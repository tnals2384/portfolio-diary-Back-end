package com.diary.domain.file.controller;

import com.diary.domain.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FileController {
    private final FileService fileService;
}
