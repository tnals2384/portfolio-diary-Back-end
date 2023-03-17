package com.diary.domain.tag.model;

import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum TagType {
    JOB("Job", "관련 직무"),
    ABILITY("Ability", "핵심 역량"),
    STACK("Stack", "사용한 기술");

    private final String code;
    private final String desc;

    public static TagType of(String code){
        return Arrays.stream(TagType.values())
                .filter(x -> x.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new RestApiException(ErrorCode.TAG_TYPE_NOT_FOUND));
    }
}
