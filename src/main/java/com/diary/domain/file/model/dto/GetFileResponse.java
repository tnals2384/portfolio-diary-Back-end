package com.diary.domain.file.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetFileResponse {
    private Long fileId;
    private String fileName;
    private String filePath;

    public static GetFileResponse of(Long fileId,String fileName, String filePath) {
        return new GetFileResponse(fileId,fileName,filePath);
    }
}
