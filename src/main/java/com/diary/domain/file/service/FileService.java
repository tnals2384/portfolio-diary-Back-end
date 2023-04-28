package com.diary.domain.file.service;

import com.diary.domain.file.model.File;
import com.diary.domain.file.model.dto.UploadFileResponse;
import com.diary.domain.post.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    UploadFileResponse uploadFiles(Long postId, List<MultipartFile> files) throws IOException;

    void deleteFiles(Post post);

    void updateFiles(Post post, List<MultipartFile> files) throws IOException;
}
