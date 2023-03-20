package com.diary.domain.experience.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateExperienceResponse {
    private Long experienceId;

    public static CreateExperienceResponse of(Long experienceId) {
        return new CreateExperienceResponse(experienceId);
    }
}
