package com.diary.domain.tag.service;

import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import com.diary.domain.member.model.Member;
import com.diary.domain.memberTag.model.MemberTag;
import com.diary.domain.memberTag.repository.MemberTagRepository;
import com.diary.domain.post.model.Post;
import com.diary.domain.post.repository.PostRepository;
import com.diary.domain.tag.model.Tag;
import com.diary.domain.tag.model.TagType;
import com.diary.domain.tag.model.dto.CreateTagResponse;
import com.diary.domain.tag.model.dto.FindTagResponse;
import com.diary.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final PostRepository postRepository;
    private final MemberTagRepository memberTagRepository;


    //태그 생성
    @Override
    @Transactional
    public CreateTagResponse createTag(Long postId, Map<String, String> tags, Member member) throws IOException {

        List<Long> tagList = new ArrayList<>();
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND)
        );

        for (Map.Entry<String, String> t : tags.entrySet()) {
            //tagType으로 변환
            TagType type = TagType.of(t.getKey());

            //newTag 만들어 저장
            Tag tag = tagRepository.save(Tag.newTag(type, t.getValue(), post));

            //member tag 관계 테이블
            memberTagRepository.save(MemberTag.of(member,tag));

            tagList.add(tag.getId());
        }

        return CreateTagResponse.of(tagList);
    }

    //태그 업데이트. (지웠다가 다시 생성)
    @Override
    @Transactional
    public void updateTags(Post post, Map<String, String> tags, Member member) throws IOException {
        deleteTags(post);
        if (!CollectionUtils.isEmpty(tags)) {
            createTag(post.getId(), tags, member);
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

    @Override
    @Transactional
    public List<FindTagResponse> findTagList(Long memberId) {
        List<FindTagResponse> responses = new ArrayList<>();

        List<TagType> tagTypes = Arrays.asList(TagType.JOB, TagType.ABILITY, TagType.STACK);
        for (TagType tagType : tagTypes) {
            responses.add(FindTagResponse.of(tagType, tagRepository.findTagNameByMemberAndTagType(memberId, tagType)));
        }

        return responses;
    }

    @Override
    @Transactional
    public List<String> findTagName(Member member, Long postId) {
        return tagRepository.findTagNameByMemberAndPost(member, postId);
    }

}
