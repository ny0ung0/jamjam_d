package com.jamjam.rest.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.jamjam.rest.service.CustomOAuth2UserService;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // CSRF 보호를 비활성화합니다.
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "signup", "/oauth2/**", "/login/**", "/error").permitAll()
                .anyRequest().permitAll() // 나머지 모든 요청을 허용합니다.
            )
            .oauth2Login(oauth2 -> oauth2
                .defaultSuccessUrl("http://localhost:9999/usermain", true) // 로그인 성공 시 리디렉션 URL을 설정합니다.
                .failureUrl("/login?error=true") // 로그인 실패 시 리디렉션 URL을 설정합니다.
                .userInfoEndpoint(userInfo -> userInfo
                    .userService(oauth2UserService()) // 사용자 정보를 가져오기 위한 서비스 설정
                )
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // 로그아웃 URL을 설정합니다.
                .logoutSuccessUrl("http://localhost:9999/") // 로그아웃 성공 시 리디렉션 URL을 설정합니다.
                .invalidateHttpSession(true) // 세션을 무효화합니다.
                .deleteCookies("JSESSIONID", "XSRF-TOKEN") // 특정 쿠키를 삭제합니다.
                .clearAuthentication(true) // 인증 정보 제거
                .addLogoutHandler((request, response, authentication) -> {
                    // 로그아웃 시 Google 로그아웃 URL로 리디렉션
                    String googleLogoutUrl = "http://localhost:9999/nindex";
                    response.setStatus(HttpServletResponse.SC_OK);
                    try {
						response.sendRedirect(googleLogoutUrl);
					} catch (IOException e) {
						e.printStackTrace();
					}
                })
            );
        
        return http.build(); // SecurityFilterChain 빌드
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService() {
        return new CustomOAuth2UserService(); // 커스텀 OAuth2UserService를 반환
    }
}
