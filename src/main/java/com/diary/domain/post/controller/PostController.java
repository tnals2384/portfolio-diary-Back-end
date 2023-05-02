package com.diary.domain.post.controller;

import com.diary.common.base.BaseResponse;
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

    @PostMapping("/api/posts")
    public BaseResponse<CreatePostResponse> createPost(
            @RequestParam @Valid Long memberId,
            @RequestPart @Valid CreatePostRequest createPostRequest,
            @RequestPart(value = "file", required = false) List<MultipartFile> files) throws IOException {

        return new BaseResponse<>(postService.createPost(memberId, createPostRequest, files));
    }

    @PutMapping("/api/posts/{postId}")
    public BaseResponse<UpdatePostResponse> updatePost(
            @RequestParam @Valid Long memberId,
            @PathVariable @Valid Long postId,
            @RequestPart @Valid UpdatePostRequest updatePostRequest,
            @RequestPart(value="file", required = false) List<MultipartFile> files) throws IOException {
        return new BaseResponse<>(postService.updatePost(memberId,postId,updatePostRequest,files));
    }


    @DeleteMapping("/api/posts/{postId}")
    public BaseResponse<DeletePostResponse> deletePost(@RequestParam @Valid Long memberId,
                                                       @PathVariable @Valid Long postId){
        return new BaseResponse<>(postService.deletePost(memberId,postId));
    }

    //상세 조회
    @GetMapping("/api/posts/{postId}")
    public BaseResponse<GetPostResponse> getPost(
            @RequestParam @Valid Long memberId,
            @PathVariable @Valid Long postId
    ) {
        return new BaseResponse<>(postService.getPost(memberId, postId));
    }

    @GetMapping("/api/posts")
    public BaseResponse<List<GetPostPageResponse>> getAllPosts(
            @RequestParam @Valid Long memberId,
            @RequestParam(defaultValue = "id", value = "orderBy") String orderType,
            @PageableDefault(size= 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        List<GetPostPageResponse> PagePost = postService.getAllPostsWithPaging(memberId,orderType,pageable).getContent();
        return new BaseResponse<>(PagePost);

    }



}
