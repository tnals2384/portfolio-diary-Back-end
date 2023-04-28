package com.diary.config.auth;

import com.diary.domain.auth.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
                //세션을 생성하지 않고, 요청마다 새로운 인증을 수행하도록 구성하는 옵션으로 REST API와 같은 환경에서 사용
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //요청에 대한 인가 처리 설정
                .authorizeRequests()
                //인증되지 않은 사용자도 접근 가능하도록 허용 (로그인, 토큰발급에는 인증이 불필요)
                .anyRequest().permitAll()
                .and()
                //OAuth 2.0 기반 인증을 처리하기위해 Provider와의 연동을 지원
                .oauth2Login()
                //인증에 성공하면 실행할 handler (redirect 시킬 목적)
                //OAuth 2.0 Provider로부터 사용자 정보를 가져오는 엔드포인트를 지정하는 메서드
                .userInfoEndpoint()
                //OAuth 2.0 인증이 처리되는데 사용될 사용자 서비스를 지정하는 메서드
                .userService(oAuthService)
                .and();

        return http.build();
    }
}