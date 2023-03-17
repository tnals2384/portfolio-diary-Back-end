package com.diary.domain.post.Service;

import com.diary.domain.post.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private PostRepository postRepository;
}
