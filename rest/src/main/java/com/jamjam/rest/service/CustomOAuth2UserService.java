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

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerUserId = oauth2User.getAttribute("sub");

        OAuthUser oauthUser = oauthUserMapper.findByProviderAndProviderUserId(provider, providerUserId);
        User user = null;
        if (oauthUser == null) {
            user = userMapper.findByEmail(oauth2User.getAttribute("email"));
            if (user == null) {
                user = new User();
                user.setEmail(oauth2User.getAttribute("email"));
                user.setName(oauth2User.getAttribute("name"));
                user.setRole("ROLE_USER");
                userMapper.insertUser(user);
                
            }
            	
       
            oauthUser = new OAuthUser(user.getUser_id(), provider, providerUserId);
            oauthUserMapper.insertOAuthUser(oauthUser);
        } else {
            user = userMapper.findByEmail(oauth2User.getAttribute("email"));
        }

        if (!user.hasAdditionalInfo()) {
            httpSession.setAttribute("additionalInfoRequired", true);
        } else {
            httpSession.removeAttribute("additionalInfoRequired");
        }

        return OAuth2UserDetails.create(user, oauth2User.getAttributes());
    }
}
