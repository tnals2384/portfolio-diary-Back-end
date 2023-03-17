package com.diary.domain.experience.Service;

import com.diary.domain.experience.Repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {
    private ExperienceRepository experienceRepository;
}
