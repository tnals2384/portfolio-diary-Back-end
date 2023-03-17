package com.diary.domain.file.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FileRepositoryCustomImpl implements FileRepositoryCustom {
    private final JPAQueryFactory queryFactory;

}
