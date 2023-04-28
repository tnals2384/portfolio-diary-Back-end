package com.diary.domain.experience.model.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateExperienceRequest {
    @NotBlank
    private Long id;

    @NotBlank
    private String title;

    private String contents;

}