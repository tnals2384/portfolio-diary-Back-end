package com.diary.domain.tag.service;

import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import com.diary.domain.post.model.Post;
import com.diary.domain.post.repository.PostRepository;
import com.diary.domain.tag.model.Tag;
import com.diary.domain.tag.model.TagType;
import com.diary.domain.tag.model.dto.CreateTagResponse;
import com.diary.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final PostRepository postRepository;


    //태그 생성
    @Override
    @Transactional
    public CreateTagResponse createTag(Long postId, Map<String, String> tags) throws IOException {

        List<Long> tagList = new ArrayList<>();
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND)
        );

        for (Map.Entry<String, String> t : tags.entrySet()) {
            //tagType으로 변환
            TagType type = TagType.of(t.getKey());

            //newTag 만들어 저장
            Tag tag = tagRepository.save(Tag.newTag(type, t.getValue(), post));

            tagList.add(tag.getId());
        }

        return CreateTagResponse.of(tagList);
    }

    //태그 업데이트. (지웠다가 다시 생성)
    @Override
    @Transactional
    public void updateTags(Post post, Map<String, String> tags) throws IOException {
        deleteTags(post);
        if (!CollectionUtils.isEmpty(tags)) {
            createTag(post.getId(), tags);
        }
    }


    //태그 삭제
    @Override
    @Transactional
    public void deleteTags(Post post) {
        List<Tag> tags = tagRepository.findAllByPost(post);

        if (!CollectionUtils.isEmpty(tags)) {
            for (Tag tag : tags) {
                tagRepository.delete(tag);
            }
        }
    }

    //getPost 시 Map<String(타입), String(태그이름)>으로 tags get 가능하도록 함
    @Override
    public Map<String, String> getTags(Post post) {
        List<Tag> tags = tagRepository.findAllByPost(post);
        Map<String, String> responseTags = new HashMap<>();

        if (!CollectionUtils.isEmpty(tags)) {
            for (Tag tag : tags) {
                responseTags.put(tag.getTagType().toString(), tag.getTagName());
            }
        }
        return responseTags;
    }
}
