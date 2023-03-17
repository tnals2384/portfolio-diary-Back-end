package com.diary.domain.file.Repository;

import com.diary.domain.member.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Member,Long>, FileRepositoryCustom {
}
