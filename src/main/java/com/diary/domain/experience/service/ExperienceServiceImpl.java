package com.diary.domain.experience.service;

import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import com.diary.domain.experience.model.Experience;
import com.diary.domain.experience.model.dto.CreateExperienceResponse;
import com.diary.domain.experience.repository.ExperienceRepository;
import com.diary.domain.post.model.Post;
import com.diary.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {
    private final ExperienceRepository experienceRepository;
    private final PostRepository postRepository;

    @Override
    public CreateExperienceResponse createExperience(Long postId, Map<String, String> experiences) throws IOException {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND));

        List<Long> exList = new LinkedList<>();

        for (Map.Entry<String, String> ex : experiences.entrySet()) {
            Experience experience = experienceRepository.save(Experience.builder()
                    .title(ex.getKey())
                    .contents(ex.getValue())
                    .post(post).build());
            exList.add(experience.getId());
        }
        return CreateExperienceResponse.of(exList);
    }
}
