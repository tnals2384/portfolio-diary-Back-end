package com.diary.domain.file.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteFileResponse {

    Long fileId;


    public static DeleteFileResponse of(Long fileId) {
        return new DeleteFileResponse(fileId);
    }
}
