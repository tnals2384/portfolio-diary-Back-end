package com.diary.domain.file.repository;

import com.diary.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Member,Long>, FileRepositoryCustom {
}
