package com.diary.domain.auth;


import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import com.diary.domain.member.model.Member;
import com.diary.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class OAuthLoginMemberUtil {

    private final MemberRepository memberRepository;

    public Member getGoogleLoginMember(Authentication authentication) {
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();
        String email = principal.getAttributes().get("email").toString();

        return memberRepository.findByEmail(email).orElseThrow(() -> new RestApiException(ErrorCode.NO_LOGIN_USER));
    }
}
