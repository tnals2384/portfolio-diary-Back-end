package com.diary.domain.auth;


import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import com.diary.domain.member.model.Member;
import com.diary.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class OAuthLoginMemberUtil {

    private final MemberRepository memberRepository;

    public Member getGoogleLoginMember() {
        OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getPrincipal().getAttributes().get("email").toString();

        return memberRepository.findByEmail(email).orElseThrow(() -> new RestApiException(ErrorCode.NO_LOGIN_USER));
    }
}
