package com.diary.domain.tag.Service;

import com.diary.domain.tag.Repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private TagRepository tagRepository;
}
