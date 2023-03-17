package com.diary.domain.post.repository;

import com.diary.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Member,Long>, PostRepositoryCustom {
}
