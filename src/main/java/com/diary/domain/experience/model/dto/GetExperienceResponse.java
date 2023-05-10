package com.diary.domain.experience.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetExperienceResponse {
    private String title;
    private String content;

    public static GetExperienceResponse of(String title, String content) {
        return new GetExperienceResponse(title,content);
    }
}
