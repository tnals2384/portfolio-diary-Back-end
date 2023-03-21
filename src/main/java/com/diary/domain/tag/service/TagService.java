package com.diary.domain.tag.service;

import com.diary.domain.post.model.dto.CreatePostRequest;
import com.diary.domain.tag.model.TagType;
import com.diary.domain.tag.model.dto.CreateTagRequest;
import com.diary.domain.tag.model.dto.CreateTagResponse;

import java.io.IOException;
import java.util.Map;

public interface TagService {
    CreateTagResponse createTag(Long postId, Map<String,String> tags) throws IOException;

}
