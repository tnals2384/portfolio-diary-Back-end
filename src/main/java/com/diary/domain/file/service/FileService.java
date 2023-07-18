package com.diary.domain.file.service;

import com.diary.domain.file.model.dto.DeleteFileResponse;
import com.diary.domain.file.model.dto.GetFileResponse;
import com.diary.domain.file.model.dto.UploadFileResponse;
import com.diary.domain.member.model.Member;
import com.diary.domain.post.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    UploadFileResponse uploadFiles(Long postId, List<MultipartFile> files) throws IOException;
    UploadFileResponse addFiles(Member loginMember, Long postId, List<MultipartFile> files) throws IOException;
    List<GetFileResponse> getFiles(Post post);
    DeleteFileResponse deleteFile(Member loginMember, Long fileId);
    void softDeleteFiles(Post post);
    void hardDeleteFiles(Post post);

    void updateFileActive(Post post);
}
