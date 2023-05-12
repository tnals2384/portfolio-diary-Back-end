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
import com.diary.domain.tag.model.dto.CreateTagRequest;
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
    public CreateTagResponse createTag(Long postId, List<CreateTagRequest> tags, Member member) throws IOException {

        List<Long> tagList = new ArrayList<>();

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND)
        );

        for (CreateTagRequest tagsInTagType : tags) {
            //tagType으로 변환
            TagType type = TagType.of(tagsInTagType.getTagType());

            //같은 type을 가진 tagName들의 List
            List<String> tagNameList = tagsInTagType.getTagName();
            for(String tagName : tagNameList) {
                //newTag 만들어 저장
                Tag tag = tagRepository.save(Tag.newTag(type, tagName, post));

                //member tag 관계 테이블
                memberTagRepository.save(MemberTag.of(member, tag));

                tagList.add(tag.getId());
            }
        }

        return CreateTagResponse.of(tagList);
    }

    //태그 업데이트. (지웠다가 다시 생성)
    @Override
    @Transactional
    public void updateTags(Post post, List<CreateTagRequest> tags, Member member) throws IOException {
        deleteTags(post);
        if (!CollectionUtils.isEmpty(tags)) {
            createTag(post.getId(), tags, member);
        }
    }


    //태그 삭제 (완전 삭제)
    @Override
    @Transactional
    public void deleteTags(Post post) {
        List<Tag> tags = tagRepository.findAllByPost(post);

        if (!CollectionUtils.isEmpty(tags)) {
            tagRepository.deleteAll(tags);
        }
    }

    //태그 삭제 (SotfDelete로 Status만 변경)
    @Override
    @Transactional
    public void softDeleteTags(Post post) {
        List<Tag> tags = tagRepository.findAllByPost(post);

        if (!CollectionUtils.isEmpty(tags)) {
            for (Tag tag : tags) {
                tag.changeStatus(tag.getStatus());
            }
        }
    }


    @Override
    public List<FindTagResponse> getTags(Long postId) {
        List<FindTagResponse> responses = new ArrayList<>();

        List<TagType> tagTypes = Arrays.asList(TagType.JOB, TagType.ABILITY, TagType.STACK);
        for (TagType tagType : tagTypes) {
            responses.add(FindTagResponse.of(tagType, tagRepository.findTagNameByTagTypeAndPost(postId, tagType)));
        }
        return responses;
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
