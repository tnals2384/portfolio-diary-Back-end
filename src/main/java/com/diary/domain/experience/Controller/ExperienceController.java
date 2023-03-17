package com.diary.domain.experience.Controller;

import com.diary.domain.experience.Service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ExperienceController {
    private final ExperienceService experienceService;
}
