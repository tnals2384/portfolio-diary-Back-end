package com.diary.domain.tag.repository;

import com.diary.domain.tag.model.TagType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.diary.domain.tag.model.QTag.tag;


@RequiredArgsConstructor
public class TagRepositoryCustomImpl implements TagRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    @Override
    public List<String> findTagNameByMemberAndTagType(Long memberId, TagType tagType) {
        return queryFactory
                .select(tag.tagName.as("tagName"))
                .from(tag)
                .where(tag.post.member.id.eq(memberId),
                        tag.tagType.eq(tagType))
                .orderBy(tag.tagName.count().desc())
                .distinct()
                .fetch();
    }
}
