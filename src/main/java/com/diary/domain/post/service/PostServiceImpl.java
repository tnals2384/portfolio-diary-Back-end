package com.diary.domain.post.service;

import com.diary.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private PostRepository postRepository;
}
