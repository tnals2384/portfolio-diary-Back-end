package com.diary.domain.post.service;

import com.diary.domain.member.model.Member;
import com.diary.domain.post.model.dto.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    CreatePostResponse createPost(Member loginMember, CreatePostRequest request, List<MultipartFile> files) throws IOException;

    UpdatePostResponse updatePost(Member loginMember, Long postId, UpdatePostRequest request) throws IOException;

    DeletePostResponse deletePost(Member loginMember, Long postId);

    GetPostResponse getPost(Member loginMember, Long postId);

    GetPagePostsResponse getAllPostsWithPaging(Member loginMember, String orderType, Pageable pageable);

    List<GetPostsResponse> findPostsByTagNames(Member loginMember, List<String> tagNames, String orderType, Pageable pageable);
}
