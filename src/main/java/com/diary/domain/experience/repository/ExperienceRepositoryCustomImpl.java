package com.diary.domain.experience.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExperienceRepositoryCustomImpl implements ExperienceRepositoryCustom {
    private final JPAQueryFactory queryFactory;

}
