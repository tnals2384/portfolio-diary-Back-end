package com.diary.domain.file.service;

import com.diary.domain.file.model.dto.GetFileResponse;
import com.diary.domain.file.model.dto.UploadFileResponse;
import com.diary.domain.member.model.Member;
import com.diary.domain.post.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileService {
    UploadFileResponse uploadFiles(Long postId, List<MultipartFile> files) throws IOException;

    void deleteFiles(Post post);

    UploadFileResponse updateFiles(Member loginMember,Long postId, List<MultipartFile> files) throws IOException;
    List<GetFileResponse> getFiles(Post post);
    void softDeleteFiles(Post post);


    }
