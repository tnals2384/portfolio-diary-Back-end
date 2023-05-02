package com.diary.domain.post.repository;

import com.diary.domain.post.model.Post;
import com.diary.domain.post.model.dto.GetPostPageResponse;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.diary.domain.post.model.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {
    private final JPAQueryFactory queryFactory;


    @Override
    public Page<Post> findAllWithPaging(Long memberId, String orderType, Pageable pageable) {
        OrderSpecifier<?> order = orderType.equals("oldest") ? post.createdAt.asc() : post.createdAt.desc();

        List<Post> result = queryFactory
                .selectFrom(post)
                .where(post.member.id.eq(memberId))
                .orderBy(order)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(post.id.count())
                .from(post).
                where(post.member.id.eq(memberId))
                .fetchFirst();

        return new PageImpl<>(result,pageable, total);
    }
}
