package com.diary.domain.tag.repository;

import com.diary.domain.post.model.Post;
import com.diary.domain.tag.model.Tag;
import com.diary.domain.tag.model.TagType;

import java.util.Optional;

public interface TagRepositoryCustom {
    Long findByTagNameInTagType(TagType type, String name, Post post);
}
