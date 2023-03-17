package com.diary.domain.member.Repository;

import com.diary.domain.member.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long>, MemberRepositoryCustom {
}
