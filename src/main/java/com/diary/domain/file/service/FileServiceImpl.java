package com.diary.domain.file.service;

import com.diary.domain.file.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private FileRepository fileRepository;
}
