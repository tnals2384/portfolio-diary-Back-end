package com.diary.domain.experience.model.dto;


import com.diary.domain.experience.model.Experience;
import com.diary.domain.member.model.Member;
import com.diary.domain.post.model.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateExperienceRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    public Experience toEntity(Post post) {
        return Experience.builder()
                .title(title)
                .contents(contents)
                .post(post)
                .build();
    }


}
