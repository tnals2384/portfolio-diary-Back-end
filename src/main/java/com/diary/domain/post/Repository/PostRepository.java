package com.diary.domain.post.Repository;

import com.diary.domain.member.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Member,Long>, PostRepositoryCustom {
}
