package com.diary.domain.post.repository;

import com.diary.domain.post.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Page<Post> findAllWithPaging(Long memberId, String orderType, Pageable pageable);
}
