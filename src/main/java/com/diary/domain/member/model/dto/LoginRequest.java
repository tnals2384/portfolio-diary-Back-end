package com.diary.domain.member.model.dto;

import com.diary.domain.member.model.Member;
import com.diary.domain.member.model.MemberRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class LoginRequest {
    private final String nickname;
    private final String email;
    private final MemberRole role;
    private final String provider;
    private final String providerId;
    private final LocalDateTime createdAt;


    public static LoginRequest from(Member member) {
        return new LoginRequest(
                member.getNickname(),
                member.getEmail(),
                member.getRole(),
                member.getProvider(),
                member.getProviderId(),
                member.getUpdatedAt()
        );
    }
}