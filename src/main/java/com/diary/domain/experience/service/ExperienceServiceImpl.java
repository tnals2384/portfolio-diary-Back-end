package com.diary.domain.experience.service;

import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import com.diary.domain.experience.model.Experience;
import com.diary.domain.experience.model.dto.*;
import com.diary.domain.experience.repository.ExperienceRepository;
import com.diary.domain.member.model.Member;
import com.diary.domain.post.model.Post;
import com.diary.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {
    private final ExperienceRepository experienceRepository;
    private final PostRepository postRepository;


    /*
    초기 Post 생성할 때 Experiences들 한꺼번에 생성하기 위한 createExperiences
     */
    @Override
    @Transactional
    public CreateExperiencesResponse createExperiences(Long postId, Map<String, String> experiences) throws IOException {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND));

        List<Long> exList = new LinkedList<>();

        for (Map.Entry<String, String> ex : experiences.entrySet()) {
            Experience experience = experienceRepository.save(
                    Experience.newExperience(ex.getKey(),ex.getValue(),post));
            exList.add(experience.getId());
        }
        return CreateExperiencesResponse.of(exList);
    }

    /*
    글 생성 후, 경험 추가를 위한 createExperience
     */
    @Override
    @Transactional
    public CreateExperienceResponse createExperience(Member loginMember,Long postId, CreateExperienceRequest request) throws IOException {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND));

        //login한 member와 post의 member가 다르면 error
        if (!loginMember.equals(post.getMember())) {
            throw new RestApiException(ErrorCode.BAD_REQUEST);
        }

        Experience experience = experienceRepository.save(
                Experience.newExperience(request.getTitle(), request.getContent(),post));
        return CreateExperienceResponse.of(experience.getId());
    }


    /*
    경험 수정을 위한 updateExperience
     */
    @Override
    @Transactional
    public UpdateExperienceResponse updateExperience(Member loginMember, Long experienceId,
                                                      UpdateExperienceRequest request) throws IOException{
        Experience experience = experienceRepository.findById(experienceId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND)
        );

        //login한 member와 post의 member가 다르면 error
        if (!loginMember.equals(experience.getPost().getMember())) {
            throw new RestApiException(ErrorCode.BAD_REQUEST);
        }

        experience.update(request.getTitle(),request.getContent());

        return UpdateExperienceResponse.of(experienceId);
    }

    public DeleteExperienceResponse deleteExperience(Member loginMember, Long experienceId) {
        Experience experience = experienceRepository.findById(experienceId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND)
        );

        //login한 member와 post의 member가 다르면 error
        if (!loginMember.equals(experience.getPost().getMember())) {
            throw new RestApiException(ErrorCode.BAD_REQUEST);
        }

        experienceRepository.delete(experience);

        return DeleteExperienceResponse.of(experienceId);
    }


    //experience 삭제 (완전 삭재)
    @Override
    @Transactional
    public void deleteExperiences(Post post) {
        List<Experience> experiences = experienceRepository.findAllByPost(post);
        if(!CollectionUtils.isEmpty(experiences)) {
            experienceRepository.deleteAll(experiences);
        }
    }

    //experience 삭제 (softDelete로 Status만 변경)
    @Override
    @Transactional
    public void softDeleteExperiences(Post post) {
        List<Experience> experiences = experienceRepository.findAllByPost(post);
        if(!CollectionUtils.isEmpty(experiences)) {
            for(Experience experience: experiences) {
                experience.changeStatus(experience.getStatus());
            }
        }
    }


    @Override
    public List<GetExperienceResponse> getExperiences(Post post) {
        List<Experience> experiences = experienceRepository.findAllByPost(post);
        List<GetExperienceResponse> responses= new ArrayList<>();

        if(!CollectionUtils.isEmpty(experiences)) {
            for(Experience ex: experiences) {
                responses.add(
                        GetExperienceResponse.of(ex.getId(),ex.getTitle(),ex.getContent())
                );
            }
        }
        return responses;
    }

    @Override
    public void hardDeleteExperiences(Post post) {
        List<Experience> experiences = experienceRepository.findAllByPost(post);
        if (experiences != null) {
            experiences.forEach(experience -> experienceRepository.delete(experience));
        }
    }


}
