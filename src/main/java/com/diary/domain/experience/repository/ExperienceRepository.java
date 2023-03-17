package com.diary.domain.experience.repository;

import com.diary.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Member,Long>, ExperienceRepositoryCustom {
}
