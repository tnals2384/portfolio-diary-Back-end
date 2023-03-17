package com.diary.domain.post.Controller;

import com.diary.domain.post.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;
}
