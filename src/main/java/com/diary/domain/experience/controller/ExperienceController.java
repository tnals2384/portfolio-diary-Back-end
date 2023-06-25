package com.diary.domain.experience.controller;

import com.diary.common.base.BaseResponse;
import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import com.diary.config.auth.MemberId;
import com.diary.domain.experience.model.dto.CreateExperienceRequest;
import com.diary.domain.experience.model.dto.CreateExperienceResponse;
import com.diary.domain.experience.model.dto.UpdateExperienceRequest;
import com.diary.domain.experience.model.dto.UpdateExperienceResponse;
import com.diary.domain.experience.service.ExperienceService;
import com.diary.domain.member.model.Member;
import com.diary.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class ExperienceController {
    private final ExperienceService experienceService;
    private final MemberRepository memberRepository;


    /*
    해당 게시글의 경험기록 추가
     */
    @PostMapping("/posts/{postId}/experiences")
    public BaseResponse<CreateExperienceResponse> createExperience(
            @MemberId Long memberId,
            @PathVariable @Valid Long postId,
            @RequestPart @Valid CreateExperienceRequest createExperienceRequest
    ) throws IOException {
        Member loginMember = memberRepository.findById(memberId).orElseThrow(() -> new RestApiException(ErrorCode.NO_LOGIN_USER));
        return new BaseResponse<>(experienceService.createExperience(loginMember, postId, createExperienceRequest));

    }


    /*
    경험기록 수정
     */
    @PutMapping("/experiences/{experienceId}")
    public BaseResponse<UpdateExperienceResponse> updateExperience(
            @MemberId Long memberId,
            @PathVariable @Valid Long experienceId,
            @RequestPart @Valid UpdateExperienceRequest updateExperienceRequest) throws IOException {

        Member loginMember = memberRepository.findById(memberId).orElseThrow(() -> new RestApiException(ErrorCode.NO_LOGIN_USER));
        return new BaseResponse<>(experienceService.updateExperience(loginMember, experienceId, updateExperienceRequest));
    }

}
