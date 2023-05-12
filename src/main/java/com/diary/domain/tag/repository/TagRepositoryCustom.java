package com.diary.domain.tag.repository;

import com.diary.domain.member.model.Member;
import com.diary.domain.tag.model.TagType;

import java.util.List;

public interface TagRepositoryCustom {
    List<String> findTagNameByMemberAndTagType(Long memberId, TagType tagType);
    List<String> findTagNameByMemberAndPost(Member member, Long postId);
    List<String> findTagNameByTagTypeAndPost(Long postId, TagType tagType);
}
