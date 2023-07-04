package com.diary.domain.post.repository;

import com.diary.domain.member.model.Member;
import com.diary.domain.post.model.Post;
import com.diary.domain.post.model.dto.GetPostsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {
    Page<Post> findAllWithPaging(Long memberId, String orderType, Pageable pageable);
    List<GetPostsResponse> findPostsByTagNames(Member loginMember, List<String> tagNames, String orderType, Pageable pageable);
}
