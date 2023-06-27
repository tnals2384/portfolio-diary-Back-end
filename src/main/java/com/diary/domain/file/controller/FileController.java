package com.diary.domain.file.controller;

import com.diary.common.base.BaseResponse;
import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import com.diary.config.auth.MemberId;
import com.diary.domain.file.model.dto.UploadFileResponse;
import com.diary.domain.file.service.FileService;
import com.diary.domain.member.model.Member;
import com.diary.domain.member.repository.MemberRepository;
import com.diary.domain.post.model.dto.UpdatePostRequest;
import com.diary.domain.post.model.dto.UpdatePostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class FileController {
    private final FileService fileService;
    private final MemberRepository memberRepository;

    @PutMapping("/posts/{postId}/file")
    public BaseResponse<UploadFileResponse> updateFile(
            @MemberId Long memberId,
            @PathVariable @Valid Long postId,
            @RequestPart(value = "file", required = false) List<MultipartFile> files) throws IOException {

        Member loginMember = memberRepository.findById(memberId).orElseThrow(() -> new RestApiException(ErrorCode.NO_LOGIN_USER));
        return new BaseResponse<>(fileService.updateFiles(loginMember, postId, files));
    }
}
