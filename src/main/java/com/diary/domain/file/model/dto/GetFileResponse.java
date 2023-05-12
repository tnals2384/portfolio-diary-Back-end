package com.diary.domain.file.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetFileResponse {
    private String fileName;
    private String filePath;

    public static GetFileResponse of(String fileName, String filePath) {
        return new GetFileResponse(fileName,filePath);
    }
}
