package com.diary.domain.experience.service;

import com.diary.domain.experience.model.dto.CreateExperienceResponse;
import com.diary.domain.experience.model.dto.UpdateExperienceRequest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ExperienceService {

    CreateExperienceResponse createExperience(Long postId, Map<String, String> experiences) throws IOException;

    void updateExperience( List<UpdateExperienceRequest> requests);
}
