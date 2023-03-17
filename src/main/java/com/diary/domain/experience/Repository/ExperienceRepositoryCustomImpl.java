package com.diary.domain.experience.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExperienceRepositoryCustomImpl implements ExperienceRepositoryCustom {
    private final JPAQueryFactory queryFactory;

}
