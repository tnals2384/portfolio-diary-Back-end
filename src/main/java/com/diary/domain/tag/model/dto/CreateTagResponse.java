package com.diary.domain.tag.model.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateTagResponse {
    private List<Long> tags;

    public static CreateTagResponse of(List<Long> tagIds) {
        return new CreateTagResponse(tagIds);
    }
}
