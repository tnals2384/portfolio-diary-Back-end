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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public CreateTagResponse createTag(Long postId, Map<String, String> tags) throws IOException {
        List<Long> tagList = new ArrayList<>();

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND)
        );

        for (Map.Entry<String, String> t : tags.entrySet()) {
            TagType type = TagType.of(t.getKey());

            Tag tag = tagRepository.save(Tag.newTag(type,t.getValue(),post));

            tagList.add(tag.getId());
        }

        return CreateTagResponse.of(tagList);
    }

}
