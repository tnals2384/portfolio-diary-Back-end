package com.diary.domain.memberTag.repository;

import com.diary.domain.experience.repository.ExperienceRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberTagRepositoryCustomImpl implements MemberTagRepositoryCustom {
    private final JPAQueryFactory queryFactory;

}
