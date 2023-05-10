package com.diary.domain.tag.model.dto;

import com.diary.domain.tag.model.TagType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FindTagResponse {
    private TagType tagType;
    private List<String> tagName;

    public static FindTagResponse of(TagType tagType, List<String> tagName) {
        return new FindTagResponse(tagType, tagName);
    }
}
