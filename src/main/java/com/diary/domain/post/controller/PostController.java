package com.diary.domain.post.controller;

import com.diary.common.base.BaseResponse;
import com.diary.domain.post.model.dto.CreatePostRequest;
import com.diary.domain.post.model.dto.CreatePostResponse;
import com.diary.domain.post.model.dto.UpdatePostRequest;
import com.diary.domain.post.model.dto.UpdatePostResponse;
import com.diary.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
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



}
