package com.diary.config.auth;

import com.diary.domain.auth.OAuthService;
import com.diary.domain.member.model.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuthService oAuthService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //CORS 설정
                .cors()
                .and()
                //Cross-Site-Request-Forgery 웹 브라우저가 신뢰할 수 없는 악성 사이트에서 사용자가 원치않는 작업을 수행하는 공격
                //쿠키에 의존하지 않고 OAuth2.0, JWT를 사용하는 REST API의 경우 CSRF 보호가 필요하지 않음
                .csrf().disable()
                //basic 인증방식은 username:password를 base64 인코딩으로 Authroization 헤더로 보내는 방식
                .httpBasic().disable()
                .formLogin().disable()
                //요청에 대한 인가 처리 설정
                .authorizeRequests()
                .antMatchers("/","/oauth2/**").permitAll() // 로그인은 누구나 가능하도록
                .antMatchers("/api/v1/**").hasRole(MemberRole.USER.name()) // 유저만 접속 가능
                .anyRequest().authenticated() // 그 외엔 모두 인증 필요
                .and()
                //OAuth 2.0 기반 인증을 처리하기위해 Provider와의 연동을 지원
                .oauth2Login()
                .userInfoEndpoint()
                //OAuth 2.0 인증이 처리되는데 사용될 사용자 서비스를 지정하는 메서드
                .userService(oAuthService);

        return http.build();
    }
}