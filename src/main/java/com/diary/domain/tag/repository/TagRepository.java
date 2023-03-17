package com.diary.domain.tag.repository;

import com.diary.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Member,Long>, TagRepositoryCustom {
}
