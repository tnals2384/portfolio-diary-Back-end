package com.diary.domain.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class OAuthController {

    private final OAuth2AuthorizedClientService clientService;

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

}

