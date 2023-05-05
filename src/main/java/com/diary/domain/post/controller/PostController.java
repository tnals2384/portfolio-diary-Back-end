package com.diary.domain.post.controller;

import com.diary.common.base.BaseResponse;
import com.diary.domain.auth.OAuthLoginMemberUtil;
import com.diary.domain.member.model.Member;
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

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;
    private final OAuthLoginMemberUtil oAuthLoginMemberUtil;

    @PostMapping("/api/posts")
    public BaseResponse<CreatePostResponse> createPost(
            @RequestPart @Valid CreatePostRequest createPostRequest,
            @RequestPart(value = "file", required = false) List<MultipartFile> files) throws IOException {
        Member loginMember = oAuthLoginMemberUtil.getGoogleLoginMember();
        return new BaseResponse<>(postService.createPost(loginMember, createPostRequest, files));
    }

    @PutMapping("/api/posts/{postId}")
    public BaseResponse<UpdatePostResponse> updatePost(
            @PathVariable @Valid Long postId,
            @RequestPart @Valid UpdatePostRequest updatePostRequest,
            @RequestPart(value = "file", required = false) List<MultipartFile> files) throws IOException {
        Member loginMember = oAuthLoginMemberUtil.getGoogleLoginMember();
        return new BaseResponse<>(postService.updatePost(loginMember, postId, updatePostRequest, files));
    }


    @DeleteMapping("/api/posts/{postId}")
    public BaseResponse<DeletePostResponse> deletePost(@PathVariable @Valid Long postId) {
        Member loginMember = oAuthLoginMemberUtil.getGoogleLoginMember();
        return new BaseResponse<>(postService.deletePost(loginMember, postId));
    }

    //상세 조회
    @GetMapping("/api/posts/{postId}")
    public BaseResponse<GetPostResponse> getPost(
            @PathVariable @Valid Long postId) {
        Member loginMember = oAuthLoginMemberUtil.getGoogleLoginMember();
        return new BaseResponse<>(postService.getPost(loginMember, postId));
    }

    //Post 목록 조회 with Paging
    @GetMapping("/api/posts")
    public BaseResponse<GetPagePostsResponse> getAllPosts(
            @RequestParam(defaultValue = "id", value = "orderBy") String orderType,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Member loginMember = oAuthLoginMemberUtil.getGoogleLoginMember();
        return new BaseResponse<>(postService.getAllPostsWithPaging(loginMember, orderType, pageable));
    }


}
