package com.diary.domain.member.controller;

import com.diary.common.base.BaseResponse;
import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import com.diary.config.auth.MemberId;
import com.diary.domain.member.model.Member;
import com.diary.domain.member.model.dto.FindMyPageUserResponse;
import com.diary.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/mypage")
    public BaseResponse<FindMyPageUserResponse> findMyPageUser(@MemberId Long memberId){
        return new BaseResponse<>(memberService.findMyPageUser(memberId));
    }

}
