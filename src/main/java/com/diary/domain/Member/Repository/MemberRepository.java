package com.diary.domain.Member.Repository;

import com.diary.domain.Member.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long>, MemberRepositoryCustom {
}
