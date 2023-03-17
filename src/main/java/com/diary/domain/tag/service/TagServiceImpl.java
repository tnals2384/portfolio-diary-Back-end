package com.diary.domain.tag.service;

import com.diary.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private TagRepository tagRepository;
}
