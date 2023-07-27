package com.diary.domain.member.service;

import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import com.diary.domain.member.model.Member;
import com.diary.domain.member.model.dto.FindMyPageUserResponse;
import com.diary.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public FindMyPageUserResponse findMyPageUser(Long memberId) {
        Member loginMember = memberRepository.findById(memberId).orElseThrow(() -> new RestApiException(ErrorCode.NO_LOGIN_USER));
        String nickname = loginMember.getNickname();
        Long daysSinceSignUp = ChronoUnit.DAYS.between(loginMember.getCreatedAt().toLocalDate(), LocalDate.now()) + 1L;

        return new FindMyPageUserResponse(nickname, daysSinceSignUp);
    }

    @Override
    public void deleteUser(Long memberId) {
        Member loginMember = memberRepository.findById(memberId).orElseThrow(() -> new RestApiException(ErrorCode.NO_LOGIN_USER));
        memberRepository.delete(loginMember);
    }
}
