package com.diary.domain.file.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UploadFileResponse {
    List<Long> files;

    public static UploadFileResponse of(List<Long> fileIds) {
        return new UploadFileResponse(fileIds);
    }

}
