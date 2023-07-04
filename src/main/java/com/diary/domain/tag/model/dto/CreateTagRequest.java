package com.diary.domain.tag.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateTagRequest {
    private String tagType;
    private List<String> tagName;

    public static CreateTagRequest of(String tagType, List<String> tagName) {
        return new CreateTagRequest(tagType, tagName);
    }
}
