package com.diary.domain.tag.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TagRepositoryCustomImpl implements TagRepositoryCustom {
    private final JPAQueryFactory queryFactory;

}
