package com.diary.domain.tag.controller;

import com.diary.config.auth.MemberId;
import com.diary.domain.tag.model.dto.FindTagResponse;
import com.diary.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class TagController {
    private final TagService tagService;

    @GetMapping("/tags")
    public List<FindTagResponse> findTagList (@MemberId Long memberId){
        return tagService.findTagList(memberId);
    }
}
