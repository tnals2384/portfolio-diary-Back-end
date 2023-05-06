package com.diary.config.auth;

import com.diary.domain.auth.OAuthService;
import com.diary.domain.member.model.MemberRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final OAuthService oAuthService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //CORS 설정
                .cors()
                .and()
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                //요청에 대한 인가 처리 설정
                .authorizeRequests()
                .antMatchers("/","/oauth2/**").permitAll() // 로그인은 누구나 가능하도록
                .antMatchers("/api/v1/**").hasRole(MemberRole.USER.name()) // 유저만 접속 가능
                .anyRequest().permitAll() // 개발 완료 전까지 모두 접속 가능하도록
                .and()
                //OAuth 2.0 기반 인증을 처리하기위해 Provider와의 연동을 지원
                .oauth2Login()
                .defaultSuccessUrl("/")
                .userInfoEndpoint()
                //OAuth 2.0 인증이 처리되는데 사용될 사용자 서비스를 지정하는 메서드
                .userService(oAuthService);

        return http.build();
    }
}