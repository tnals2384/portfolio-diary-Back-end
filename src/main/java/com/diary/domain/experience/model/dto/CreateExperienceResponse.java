package com.diary.domain.experience.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateExperienceResponse {
    private List<Long> experiences;

    public static CreateExperienceResponse of(List<Long> experiencesId) {
        return new CreateExperienceResponse(experiencesId);
    }
}
