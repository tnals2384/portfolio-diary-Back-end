package com.diary.domain.auth;

import com.diary.common.base.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequiredArgsConstructor
public class OAuthController {

    private final OAuth2AuthorizedClientService clientService;

    /*
    로그인된 유저의 access token을 반환합니다.
    * */
     /*
    로그인된 유저의 access token을 반환합니다.
    * */
    @GetMapping("/token")
    public String getGoogleMemberToken(OAuth2AuthenticationToken authentication) {
        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient("google", authentication.getName());
        if (client == null) {
            return "클라이언트 없음";
        }
        return client.getAccessToken().getTokenValue();
    }

    @GetMapping("/login-success")
    public void loginSuccess(Authentication authentication,HttpServletResponse response) throws IOException {
        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient("google", authentication.getName());
        boolean isLoggedIn = authentication != null && authentication.isAuthenticated();
        // 토큰을 쿠키에 저장
        Cookie cookie = new Cookie("accessToken",String.valueOf(isLoggedIn) );
        cookie.setMaxAge(3600); // 쿠키 유효기간 설정 (예: 1시간)
        cookie.setPath("/"); // 모든 경로에서 쿠키 접근 가능하도록 설정
        response.addCookie(cookie);
        // 리다이렉트 URL을 생성하고 토큰을 포함시킴
        String redirectUrl = "http://ec2-54-79-176-242.ap-southeast-2.compute.amazonaws.com:8080";

        response.sendRedirect(redirectUrl);
    }


}

