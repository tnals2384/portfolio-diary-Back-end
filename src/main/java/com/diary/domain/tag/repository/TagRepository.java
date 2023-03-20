package com.diary.domain.tag.repository;

import com.diary.domain.member.model.Member;
import com.diary.domain.tag.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long>, TagRepositoryCustom {
}
