package com.diary.domain.experience.controller;

import com.diary.domain.experience.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ExperienceController {
    private final ExperienceService experienceService;
}
