package com.diary.domain.tag.repository;

import com.diary.common.base.BaseStatus;
import com.diary.domain.member.model.Member;
import com.diary.domain.post.model.dto.GetPostsResponse;
import com.diary.domain.tag.model.TagType;
import com.diary.domain.tag.model.dto.FindTagResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.diary.domain.member.model.QMember.member;
import static com.diary.domain.memberTag.model.QMemberTag.memberTag;
import static com.diary.domain.post.model.QPost.post;
import static com.diary.domain.tag.model.QTag.tag;


@RequiredArgsConstructor
public class TagRepositoryCustomImpl implements TagRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    @Override
    public List<String> findTagNameByMemberAndTagType(Long memberId, TagType tagType) {
        return queryFactory
                .select(memberTag.tag.tagName)
                .from(memberTag)
                .join(member).on(member.id.eq(memberId))
                .where(memberTag.tag.tagType.eq(tagType)
                    ,memberTag.tag.status.eq(BaseStatus.ACTIVE))
                .groupBy(memberTag.tag.tagName)
                .orderBy(memberTag.tag.tagName.count().desc())
                .fetch();
    }

    @Override
    public List<String> findTagNameByMemberAndPost(Member member, Long postId) {
        return queryFactory
                .select(memberTag.tag.tagName)
                .from(memberTag)
                .where(memberTag.member.eq(member),
                        memberTag.tag.post.id.eq(postId)
                        ,memberTag.tag.status.eq(BaseStatus.ACTIVE))
                .groupBy(memberTag.tag.tagName)
                .orderBy(memberTag.tag.tagName.count().desc())
                .fetch();
    }

    @Override
    public List<String> findTagNameByPostAndTagType(Long postId, TagType tagType) {
        return queryFactory
                .select(tag.tagName)
                .from(tag)
                .where(tag.post.id.eq(postId),
                        tag.tagType.eq(tagType))
                .groupBy(tag.tagName)
                .orderBy(tag.tagName.count().desc())
                .fetch();
    }
}
