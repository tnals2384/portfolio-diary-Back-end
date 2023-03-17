package com.diary.domain.file.Controller;

import com.diary.domain.file.Service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FileController {
    private final FileService fileService;
}
