package com.diary.domain.experience.service;

import com.diary.domain.experience.model.dto.CreateExperienceRequest;
import com.diary.domain.experience.model.dto.CreateExperienceResponse;

import java.io.IOException;
import java.util.Map;

public interface ExperienceService {
    public CreateExperienceResponse createExperience(Long postId, Map<String,String> experiences) throws IOException;
}
