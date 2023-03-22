package com.diary.domain.post.service;

import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import com.diary.domain.experience.model.dto.CreateExperienceRequest;
import com.diary.domain.experience.service.ExperienceService;
import com.diary.domain.file.service.FileService;
import com.diary.domain.member.model.Member;
import com.diary.domain.member.repository.MemberRepository;
import com.diary.domain.post.model.Post;
import com.diary.domain.post.model.dto.CreatePostRequest;
import com.diary.domain.post.model.dto.CreatePostResponse;
import com.diary.domain.post.repository.PostRepository;
import com.diary.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ExperienceService experienceService;
    private final TagService tagService;
    private final FileService fileService;
    @Override
    @Transactional
    public CreatePostResponse createPost(Long memberId, CreatePostRequest postRequest
    ,  List<MultipartFile> files) throws IOException {
        //memberId로 member 조회
        Member member = memberRepository.findById(memberId).orElseThrow(
                ()-> new RestApiException(ErrorCode.NOT_FOUND)
        );

        //post 저장
        Post post = postRepository.save(postRequest.toEntity(member));


        //tag 저장
        if(!postRequest.getTags().isEmpty()) {
            tagService.createTag(post.getId(),postRequest.getTags());
        }

        //experience 저장
        if(!postRequest.getExperiences().isEmpty()) {
            experienceService.createExperience(post.getId(), postRequest.getExperiences());
        }
        //file 저장
        if(!CollectionUtils.isEmpty(files)) {
            System.out.println("왜안돼!!!");
            fileService.uploadFiles(post.getId(),files);
        }
        return CreatePostResponse.of(post.getId());
    }
}
