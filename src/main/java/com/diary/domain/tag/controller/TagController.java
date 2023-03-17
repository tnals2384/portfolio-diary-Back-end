package com.diary.domain.tag.controller;

import com.diary.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TagController {
    private final TagService tagService;
}
