package com.diary.domain.experience.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateExperiencesResponse {
    private List<Long> experiences;

    public static CreateExperiencesResponse of(List<Long> experiencesId) {
        return new CreateExperiencesResponse(experiencesId);
    }
}
