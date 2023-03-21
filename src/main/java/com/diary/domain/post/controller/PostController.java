package com.diary.domain.post.controller;

import com.diary.common.base.BaseResponse;
import com.diary.domain.experience.model.dto.CreateExperienceRequest;
import com.diary.domain.experience.model.dto.CreateExperienceResponse;
import com.diary.domain.experience.service.ExperienceService;
import com.diary.domain.post.model.dto.CreatePostRequest;
import com.diary.domain.post.model.dto.CreatePostResponse;
import com.diary.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;
    @PostMapping("/api/posts")
    public BaseResponse<CreatePostResponse> createPost(
            @RequestParam @Valid Long memberId, @RequestBody @Valid CreatePostRequest createPostRequest) throws IOException {

        return new BaseResponse<>(postService.createPost(memberId,createPostRequest));
    }

}
