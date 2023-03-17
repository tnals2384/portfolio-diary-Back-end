package com.diary.domain.post.controller;

import com.diary.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;
}
