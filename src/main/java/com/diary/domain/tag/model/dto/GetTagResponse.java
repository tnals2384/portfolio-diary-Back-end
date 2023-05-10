package com.diary.domain.tag.model.dto;

import com.diary.domain.tag.model.TagType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetTagResponse {
    private String tagType;
    private String tagName;

    public static GetTagResponse of(String tagType,String tagName) {
        return new GetTagResponse(tagType,tagName);
    }
}
