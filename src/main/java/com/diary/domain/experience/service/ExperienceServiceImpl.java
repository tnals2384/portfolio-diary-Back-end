package com.diary.domain.experience.service;

import com.diary.domain.experience.repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {
    private ExperienceRepository experienceRepository;
}
