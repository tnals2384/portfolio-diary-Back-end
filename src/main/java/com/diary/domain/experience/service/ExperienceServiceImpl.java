package com.diary.domain.experience.service;

import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import com.diary.domain.experience.model.Experience;
import com.diary.domain.experience.model.dto.CreateExperienceRequest;
import com.diary.domain.experience.model.dto.CreateExperienceResponse;
import com.diary.domain.experience.repository.ExperienceRepository;
import com.diary.domain.post.model.Post;
import com.diary.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {
    private final ExperienceRepository experienceRepository;
    private final PostRepository postRepository;
    @Override
    public CreateExperienceResponse createExperience(Long postId,CreateExperienceRequest request) throws IOException {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new RestApiException(ErrorCode.NOT_FOUND));
        Experience experience = experienceRepository.save(request.toEntity(post));

        return CreateExperienceResponse.of(experience.getId());
    }
}
