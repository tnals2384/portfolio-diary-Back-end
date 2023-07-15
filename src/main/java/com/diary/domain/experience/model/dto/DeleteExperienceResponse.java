package com.diary.domain.experience.model.dto;

import com.diary.domain.post.model.dto.DeletePostResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteExperienceResponse {
    Long experienceId;

    public static DeleteExperienceResponse of(Long experienceId) {

        return new DeleteExperienceResponse(experienceId);
    }
}
