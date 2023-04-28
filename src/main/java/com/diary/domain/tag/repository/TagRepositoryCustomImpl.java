package com.diary.domain.tag.repository;

import static com.diary.domain.tag.model.QTag.tag;

import com.diary.domain.post.model.Post;
import com.diary.domain.tag.model.TagType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class TagRepositoryCustomImpl implements TagRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    /*
    중복 태그 검사를 위한 조회
     */
    @Override
    public Long findByTagNameInTagType(TagType type, String name, Post post) {

        return queryFactory.select(tag.id).from(tag)
                .where(
                        tag.tagType.eq(type),
                        tag.tagName.eq(name),
                        tag.post.eq(post)
                ).fetchOne();
    }
}
