package com.diary.domain.experience.model.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateExperienceRequest {

    private String title;
    private String content;

}
