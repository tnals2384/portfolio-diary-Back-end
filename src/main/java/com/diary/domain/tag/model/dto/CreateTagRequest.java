package com.diary.domain.tag.model.dto;

import com.diary.domain.tag.model.TagType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateTagRequest {

    //tag type과 tag 이름을 map으로 입력받음
    private Map<@NotBlank TagType, @NotBlank String> tags;

    public static CreateTagRequest of(Map<TagType,String> tags){
        return new CreateTagRequest(tags);
    }
}
