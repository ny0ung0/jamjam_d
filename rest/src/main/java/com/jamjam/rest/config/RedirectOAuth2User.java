package com.jamjam.rest.config;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class RedirectOAuth2User implements OAuth2User {

    private String redirectUrl;
    private Map<String, Object> attributes;

    public RedirectOAuth2User(String redirectUrl, Map<String, Object> attributes) {
        this.redirectUrl = redirectUrl;
        this.attributes = attributes;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
