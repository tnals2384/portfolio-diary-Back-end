package com.diary.domain.post.service;

import com.diary.domain.experience.model.dto.CreateExperienceRequest;
import com.diary.domain.post.model.dto.CreatePostRequest;
import com.diary.domain.post.model.dto.CreatePostResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    CreatePostResponse createPost(Long memberId, CreatePostRequest request, List<MultipartFile> files) throws IOException;
}
