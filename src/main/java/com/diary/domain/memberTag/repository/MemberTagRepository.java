package com.diary.domain.memberTag.repository;

import com.diary.domain.memberTag.model.MemberTag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberTagRepository extends JpaRepository<MemberTag,Long>, MemberTagRepositoryCustom {

}
