package com.jamjam.rest.config;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.jamjam.rest.dto.User;

public class OAuth2UserDetails implements OAuth2User {

    private User user; // 사용자 정보
    private Map<String, Object> attributes; // OAuth2 제공자의 사용자 속성

    public static OAuth2UserDetails create(User user, Map<String, Object> attributes) {
        OAuth2UserDetails userDetails = new OAuth2UserDetails();
        userDetails.user = user;
        userDetails.attributes = attributes;
        return userDetails;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes; // OAuth2 사용자 속성 반환
    }

    @Override
    public String getName() {
        return user.getName();  // 사용자 이름 반환
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 기본적으로 사용자가 USER 권한을 가진다고 가정합니다.
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}

