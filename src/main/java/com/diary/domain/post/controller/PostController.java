package com.diary.domain.post.controller;

import com.diary.common.base.BaseResponse;
import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import com.diary.config.auth.MemberId;
import com.diary.domain.member.model.Member;
import com.diary.domain.member.repository.MemberRepository;
import com.diary.domain.post.model.dto.*;
import com.diary.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;
    private final MemberRepository memberRepository;

    @PostMapping("/posts")
    public BaseResponse<CreatePostResponse> createPost(
            @MemberId Long memberId,
            @RequestPart @Valid CreatePostRequest createPostRequest,
            @RequestPart(value = "file", required = false) List<MultipartFile> files) throws IOException {

        Member loginMember = memberRepository.findById(memberId).orElseThrow(() -> new RestApiException(ErrorCode.NO_LOGIN_USER));
        return new BaseResponse<>(postService.createPost(loginMember, createPostRequest, files));
    }

    @PutMapping("/posts/{postId}")
    public BaseResponse<UpdatePostResponse> updatePost(
            @MemberId Long memberId,
            @PathVariable @Valid Long postId,
            @RequestPart @Valid UpdatePostRequest updatePostRequest) throws IOException {

        Member loginMember = memberRepository.findById(memberId).orElseThrow(() -> new RestApiException(ErrorCode.NO_LOGIN_USER));
        return new BaseResponse<>(postService.updatePost(loginMember, postId, updatePostRequest));
    }


    @DeleteMapping("/posts/{postId}")
    public BaseResponse<DeletePostResponse> deletePost(@MemberId Long memberId, @PathVariable @Valid Long postId) {

        Member loginMember = memberRepository.findById(memberId).orElseThrow(() -> new RestApiException(ErrorCode.NO_LOGIN_USER));
        return new BaseResponse<>(postService.deletePost(loginMember, postId));
    }

    //상세 조회
    @GetMapping("/posts/{postId}")
    public BaseResponse<GetPostResponse> getPost(@MemberId Long memberId, @PathVariable @Valid Long postId) {

        Member loginMember = memberRepository.findById(memberId).orElseThrow(() -> new RestApiException(ErrorCode.NO_LOGIN_USER));
        return new BaseResponse<>(postService.getPost(loginMember, postId));
    }

    //Post 목록 조회 with Paging
    @GetMapping("/posts")
    public BaseResponse<GetPagePostsResponse> getAllPosts(
            @MemberId Long memberId,
            @RequestParam(defaultValue = "id", value = "orderBy") String orderType,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Member loginMember = memberRepository.findById(memberId).orElseThrow(() -> new RestApiException(ErrorCode.NO_LOGIN_USER));
        return new BaseResponse<>(postService.getAllPostsWithPaging(loginMember, orderType, pageable));
    }

    //삭제된 Post 목록 조회 with Paging
    @GetMapping("in-active/posts")
    public BaseResponse<GetPagePostsResponse> getAllRemovePosts(
            @MemberId Long memberId,
            @PageableDefault(size = 10, sort = "updateAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Member loginMember = memberRepository.findById(memberId).orElseThrow(() -> new RestApiException(ErrorCode.NO_LOGIN_USER));
        return new BaseResponse<>(postService.getAllRemovePostsWithPaging(loginMember, pageable));
    }

    //Post tag별 목록 조회
    @GetMapping("/posts/tag")
    public BaseResponse<GetPagePostsResponse> findPostsByTagNames(
            @MemberId Long memberId,
            @RequestParam("tag") List<String> tagNames,
            @RequestParam(defaultValue = "id", value = "orderBy") String orderType,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Member loginMember = memberRepository.findById(memberId).orElseThrow(() -> new RestApiException(ErrorCode.NO_LOGIN_USER));
        return new BaseResponse<>(postService.findPostsByTagNames(loginMember, tagNames, orderType, pageable));
    }

}
