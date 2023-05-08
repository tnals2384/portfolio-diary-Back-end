package com.diary.domain.tag.repository;

import com.diary.domain.tag.model.TagType;

import java.util.List;

public interface TagRepositoryCustom {
    List<String> findTagNameByMemberAndTagType(Long memberId, TagType tagType);
}
