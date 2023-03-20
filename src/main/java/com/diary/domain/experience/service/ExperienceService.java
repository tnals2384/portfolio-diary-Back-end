package com.diary.domain.experience.service;

import com.diary.domain.experience.model.dto.CreateExperienceRequest;
import com.diary.domain.experience.model.dto.CreateExperienceResponse;

import java.io.IOException;

public interface ExperienceService {
    public CreateExperienceResponse createExperience(Long postId,CreateExperienceRequest request) throws IOException;
}
