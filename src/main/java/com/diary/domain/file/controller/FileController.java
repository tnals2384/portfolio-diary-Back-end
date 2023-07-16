package com.diary.domain.file.controller;

import com.diary.common.base.BaseResponse;
import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import com.diary.config.auth.MemberId;
import com.diary.domain.file.model.dto.DeleteFileResponse;
import com.diary.domain.file.model.dto.GetFileResponse;
import com.diary.domain.file.model.dto.UploadFileResponse;
import com.diary.domain.file.service.FileService;
import com.diary.domain.member.model.Member;
import com.diary.domain.member.repository.MemberRepository;
import com.diary.domain.post.model.Post;
import com.diary.domain.post.model.dto.UpdatePostRequest;
import com.diary.domain.post.model.dto.UpdatePostResponse;
import com.diary.domain.post.repository.PostRepository;
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
    private final PostRepository postRepository;


    @GetMapping("/posts/{postId}/files")
    public BaseResponse<List<GetFileResponse>> getFiles(@MemberId Long memberId,
                                                        @PathVariable @Valid Long postId) {

        Member loginMember = memberRepository.findById(memberId).orElseThrow(() -> new RestApiException(ErrorCode.NO_LOGIN_USER));
        Post post = postRepository.findById(postId).orElseThrow(() -> new RestApiException(ErrorCode.NOT_FOUND));

        //login한 member와 post의 member가 다르면 error
        if (!loginMember.equals(post.getMember())) {
            throw new RestApiException(ErrorCode.BAD_REQUEST);
        }
        return new BaseResponse<>(fileService.getFiles(post));
    }

    @PutMapping("/posts/{postId}/files")
    public BaseResponse<UploadFileResponse> addFiles(
            @MemberId Long memberId,
            @PathVariable @Valid Long postId,
            @RequestPart(value = "file", required = false) List<MultipartFile> files) throws IOException {

        Member loginMember = memberRepository.findById(memberId).orElseThrow(() -> new RestApiException(ErrorCode.NO_LOGIN_USER));
        return new BaseResponse<>(fileService.addFiles(loginMember, postId, files));
    }

    @DeleteMapping("/files/{fileId}")
    public BaseResponse<DeleteFileResponse> deleteFiles(
            @MemberId Long memberId,
            @PathVariable @Valid Long fileId) throws IOException {

        Member loginMember = memberRepository.findById(memberId).orElseThrow(() -> new RestApiException(ErrorCode.NO_LOGIN_USER));
        return new BaseResponse<>(fileService.deleteFile(loginMember, fileId));
    }
}
