package com.diary.domain.experience.service;

import com.diary.domain.experience.model.dto.CreateExperienceResponse;
import com.diary.domain.experience.model.dto.UpdateExperienceRequest;
import com.diary.domain.post.model.Post;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ExperienceService {

    CreateExperienceResponse createExperiences(Long postId, Map<String, String> experiences) throws IOException;

    void updateExperiences(List<UpdateExperienceRequest> requests) throws IOException;

    void deleteExperiences(Post post);
    void softDeleteExperiences(Post post);

    Map<String, String> getExperiences(Post post);
}
