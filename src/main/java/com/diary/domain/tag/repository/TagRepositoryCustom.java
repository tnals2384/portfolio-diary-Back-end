package com.diary.domain.tag.repository;

import com.diary.domain.post.model.Post;
import com.diary.domain.tag.model.TagType;


public interface TagRepositoryCustom {
    Long findByTagNameInTagType(TagType type, String name, Post post);
}
