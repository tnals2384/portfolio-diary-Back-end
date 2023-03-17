package com.diary.domain.tag.Controller;

import com.diary.domain.tag.Service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TagController {
    private final TagService tagService;
}
