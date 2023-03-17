package com.diary.domain.tag.Repository;

import com.diary.domain.member.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Member,Long>, TagRepositoryCustom {
}
