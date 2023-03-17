package com.diary.domain.file.Service;

import com.diary.domain.file.Repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private FileRepository fileRepository;
}
