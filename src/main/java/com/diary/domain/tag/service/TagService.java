package com.diary.domain.tag.service;

import com.diary.domain.member.model.Member;
import com.diary.domain.post.model.Post;
import com.diary.domain.tag.model.dto.CreateTagResponse;
import com.diary.domain.tag.model.dto.FindTagResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TagService {
    CreateTagResponse createTag(Long postId, Map<String,String> tags, Member member) throws IOException;
    void updateTags(Post post, Map<String, String> tags, Member member) throws IOException;
    void deleteTags(Post post);
    Map<String, String> getTags(Post post);
    List<FindTagResponse> findTagList(Long memberId);
    List<String> findTagName(Member member, Long postId);
}
