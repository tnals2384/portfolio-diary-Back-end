package com.diary.domain.experience.Repository;

import com.diary.domain.member.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Member,Long>, ExperienceRepositoryCustom {
}
