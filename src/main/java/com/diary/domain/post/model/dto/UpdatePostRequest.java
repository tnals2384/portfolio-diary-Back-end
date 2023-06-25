package com.diary.domain.post.model.dto;

import com.diary.domain.experience.model.dto.UpdateExperienceRequest;
import com.diary.domain.tag.model.dto.CreateTagRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdatePostRequest {
    @NotBlank
    private String title;
    @NotNull
    private LocalDateTime beginAt;
    @NotNull
    private LocalDateTime finishAt;

    private List<CreateTagRequest> tags;

    //private List<UpdateExperienceRequest> experiences;
    //새로 추가하는 experiences
    //private Map<@NotEmpty String, @NotBlank String> newExperiences;
}
