package com.diary.domain.post.service;

import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import com.diary.domain.experience.model.dto.GetExperienceResponse;
import com.diary.domain.experience.service.ExperienceService;
import com.diary.domain.file.model.dto.GetFileResponse;
import com.diary.domain.file.service.FileService;
import com.diary.domain.member.model.Member;
import com.diary.domain.post.model.Post;
import com.diary.domain.post.model.dto.*;
import com.diary.domain.post.repository.PostRepository;
import com.diary.domain.tag.model.dto.FindTagResponse;
import com.diary.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ExperienceService experienceService;
    private final TagService tagService;
    private final FileService fileService;

    @Override
    @Transactional
    public CreatePostResponse createPost(Member loginMember, CreatePostRequest postRequest
            , List<MultipartFile> files) throws IOException {

        //post 저장
        Post post = postRepository.save(postRequest.toEntity(loginMember));

        //tag 저장
        if (!postRequest.getTags().isEmpty()) {
            tagService.createTag(post.getId(), postRequest.getTags(), loginMember);
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
    public UpdatePostResponse updatePost(Member loginMember, Long postId, UpdatePostRequest request,
                                         List<MultipartFile> files) throws IOException {
        //postId로 post 조회
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND)
        );

        //login한 member와 post의 member가 다르면 error
        if (!loginMember.equals(post.getMember())) {
            throw new RestApiException(ErrorCode.BAD_REQUEST);
        }
        //post update
        post.update(request.getTitle(), request.getBeginAt(), request.getFinishAt());

        //experience update
        experienceService.updateExperiences(request.getExperiences());
        if (!CollectionUtils.isEmpty(request.getNewExperiences())) {
            experienceService.createExperiences(postId, request.getNewExperiences());
        }

        //file update
        fileService.updateFiles(post, files);

        //tag update
        tagService.updateTags(post, request.getTags(), loginMember);

        return UpdatePostResponse.of(postId);
    }

    @Override
    @Transactional
    public DeletePostResponse deletePost(Member loginMember, Long postId) {

        //postId로 post 조회
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND)
        );

        if (loginMember.equals(post.getMember())) {
            experienceService.softDeleteExperiences(post);
            tagService.softDeleteTags(post);
            fileService.softDeleteFiles(post);
            post.changeStatus(post.getStatus());

            return DeletePostResponse.of(postId);
        } else {
            return DeletePostResponse.of(null);
        }
    }

    //post 상세 조회
    @Override
    @Transactional
    public GetPostResponse getPost(Member loginMember, Long postId) {

        //postId로 post 조회
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND)
        );

        //login한 member와 post의 member가 다르면 error
        if (!loginMember.equals(post.getMember())) {
            throw new RestApiException(ErrorCode.BAD_REQUEST);
        }

        //experiences, tags, files 받아오기
        List<GetExperienceResponse> experiences = experienceService.getExperiences(post);
        List<FindTagResponse> tags = tagService.getTags(post);
        List<GetFileResponse> files = fileService.getFiles(post);

        return GetPostResponse.of(postId, post.getTitle(), post.getBeginAt(), post.getFinishAt(),
                experiences, tags, files);
    }


    //모든 Post 페이징 조회
    @Override
    @Transactional
    public GetPagePostsResponse getAllPostsWithPaging(Member loginMember, String orderType, Pageable pageable) {

        Page<Post> list = postRepository.findAllWithPaging(loginMember.getId(), orderType, pageable);

        int totalPages = list.getTotalPages();
        int totalPosts = (int) list.getTotalElements(); //long형을 int형으로 받음

        //받아온 Page<Post> list를 GetPostsResponse dto 형식으로 변환하여 list에 저장
        List<GetPostsResponse> pagePosts = list.map(
                post -> GetPostsResponse.of(
                        post.getId(),
                        post.getTitle(),
                        post.getBeginAt(),
                        post.getFinishAt(),
                        tagService.findTagName(loginMember, post.getId())
                )
        ).getContent();

        //totalPage, totalPosts 를 포함하여 GetPagePostsResponse 로 반환
        return GetPagePostsResponse.of(pagePosts, totalPages, totalPosts);

    }

    @Override
    @Transactional
    public List<GetPostsResponse> findPostsByTagName(Member loginMember, String tagName, String orderType, Pageable pageable) {
        List<GetPostsResponse> responses = postRepository.findPostsByTagName(loginMember, tagName, orderType, pageable);
        for (GetPostsResponse response : responses) {
            response.updateTagName(tagService.findTagName(loginMember, response.getPostId()));
        }
        return responses;
    }
}
