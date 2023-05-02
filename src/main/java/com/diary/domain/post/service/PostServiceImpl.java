package com.diary.domain.post.service;

import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import com.diary.domain.experience.model.Experience;
import com.diary.domain.experience.repository.ExperienceRepository;
import com.diary.domain.experience.service.ExperienceService;
import com.diary.domain.file.repository.FileRepository;
import com.diary.domain.file.service.FileService;
import com.diary.domain.member.model.Member;
import com.diary.domain.member.repository.MemberRepository;
import com.diary.domain.post.model.Post;
import com.diary.domain.post.model.dto.*;
import com.diary.domain.post.repository.PostRepository;
import com.diary.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ExperienceService experienceService;
    private final TagService tagService;
    private final FileService fileService;
    private final FileRepository fileRepository;

    @Override
    @Transactional
    public CreatePostResponse createPost(Long memberId, CreatePostRequest postRequest
            , List<MultipartFile> files) throws IOException {
        //memberId로 member 조회
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND)
        );

        //post 저장
        Post post = postRepository.save(postRequest.toEntity(member));

        //tag 저장
        if (!postRequest.getTags().isEmpty()) {
            tagService.createTag(post.getId(), postRequest.getTags());
        }

        //experience 저장
        if (!postRequest.getExperiences().isEmpty()) {
            experienceService.createExperiences(post.getId(), postRequest.getExperiences());
        }

        //file 저장
        if (!CollectionUtils.isEmpty(files)) {
            fileService.uploadFiles(post.getId(), files);
        }

        return CreatePostResponse.of(post.getId());
    }

    @Override
    @Transactional
    public UpdatePostResponse updatePost(Long memberId, Long postId, UpdatePostRequest request, List<MultipartFile> files) throws IOException {
        //memberId로 member 조회
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND)
        );

        //postId로 post 조회
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND)
        );

        //post update
        post.update(request.getTitle(),request.getBeginAt(),request.getFinishAt());

        //experience update
        experienceService.updateExperiences(request.getExperiences());
        if(!CollectionUtils.isEmpty(request.getNewExperiences())) {
            experienceService.createExperiences(postId, request.getNewExperiences());
        }

        //file update
        fileService.updateFiles(post, files);

        //tag update
        tagService.updateTags(post,request.getTags());



        return UpdatePostResponse.of(postId);
    }

    @Override
    @Transactional
    public DeletePostResponse deletePost(Long memberId, Long postId) {
        //memberId로 member 조회
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND)
        );

        //postId로 post 조회
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND)
        );

        if(member.equals(post.getMember())) {
            experienceService.deleteExperiences(post);
            tagService.deleteTags(post);
            fileService.deleteFiles(post);
            postRepository.delete(post);

            return DeletePostResponse.of(postId);
        }
        else {
             return DeletePostResponse.of(null);
        }
    }

    //post 상세 조회
    @Override
    @Transactional
    public GetPostResponse getPost(Long memberId, Long postId) {
        //memberId로 member 조회
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND)
        );

        //postId로 post 조회
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND)
        );

        //memberId로 조회한 member와 post의 member가 다르면 error
        if(!member.equals(post.getMember())) {
            throw new RestApiException(ErrorCode.BAD_REQUEST);
        }

        //Map으로 experiences, tags, files 받아오기
        Map<String,String> experiences = experienceService.getExperiences(post);
        Map<String,String> tags = tagService.getTags(post);
        Map<String,String> files = fileService.getFiles(post);

        return GetPostResponse.of(postId,post.getTitle(),post.getBeginAt(),post.getFinishAt(),
                experiences, tags, files);
    }

    @Override
    @Transactional
    public Page<GetPostPageResponse> getAllPostsWithPaging(Long memberId, String orderType,Pageable pageable) {
        Page<Post> list = postRepository.findAllWithPaging(memberId,orderType,pageable);
        return list.map(
                post -> GetPostPageResponse.of(
                        post.getId(),
                        post.getTitle(),
                        post.getBeginAt(),
                        post.getFinishAt(),
                        tagService.getTags(post)
                )
        );

    }
}
