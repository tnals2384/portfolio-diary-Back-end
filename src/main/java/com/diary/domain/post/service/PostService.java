package com.diary.domain.post.service;

import com.diary.domain.post.model.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    CreatePostResponse createPost(Long memberId, CreatePostRequest request, List<MultipartFile> files) throws IOException;
    UpdatePostResponse updatePost(Long memberId,Long postId, UpdatePostRequest request, List<MultipartFile> files) throws IOException;
    DeletePostResponse deletePost(Long memberId, Long postId);
}
