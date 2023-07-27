package com.diary.domain.member.service;

import com.diary.domain.member.model.dto.FindMyPageUserResponse;

public interface MemberService {
    FindMyPageUserResponse findMyPageUser(Long memberId);

    void deleteUser(Long memberId);
}
