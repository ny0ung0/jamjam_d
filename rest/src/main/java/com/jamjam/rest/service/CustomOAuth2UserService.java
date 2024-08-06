package com.jamjam.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.jamjam.rest.config.OAuth2UserDetails;
import com.jamjam.rest.dao.OAuthUserMapper;
import com.jamjam.rest.dao.UserDao;
import com.jamjam.rest.dto.OAuthUser;
import com.jamjam.rest.dto.User;

import jakarta.servlet.http.HttpSession;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private UserDao userMapper; 

    @Autowired
    private OAuthUserMapper oauthUserMapper;

    @Autowired
    private HttpSession httpSession;

    private final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = delegate.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId(); // OAuth2 제공자 (예: google, facebook)
        String providerUserId = oauth2User.getAttribute("sub"); // 제공자 사용자 ID

        OAuthUser oauthUser = oauthUserMapper.findByProviderAndProviderUserId(provider, providerUserId); // DB에서 OAuth 사용자 검색
        User user = null;
        if (oauthUser == null) {
            user = userMapper.findByEmail(oauth2User.getAttribute("email")); // 이메일로 사용자 검색
            if (user == null) {
            	// 새 사용자 생성
                user = new User();
                user.setEmail(oauth2User.getAttribute("email"));
                user.setName(oauth2User.getAttribute("name"));
                user.setRole("ROLE_USER");
                userMapper.insertUser(user); // DB에 사용자 저장
                
            }
            	
       
            oauthUser = new OAuthUser(user.getUser_id(), provider, providerUserId);
            oauthUserMapper.insertOAuthUser(oauthUser); // DB에 OAuth 사용자 저장
        } else {
            user = userMapper.findByEmail(oauth2User.getAttribute("email")); // OAuth 사용자에 대한 이메일로 사용자 검색
        }

        if (!user.hasAdditionalInfo()) {
        	 // 추가 정보가 필요한 경우 세션에 플래그 설정
            httpSession.setAttribute("additionalInfoRequired", true);
        } else {
        	// 추가 정보가 이미 있는 경우 세션 플래그 제거
            httpSession.removeAttribute("additionalInfoRequired");
        }

        return OAuth2UserDetails.create(user, oauth2User.getAttributes());  // 사용자 정보 반환
    }
}
