package com.diary.domain.tag.repository;

import com.diary.domain.file.model.File;
import com.diary.domain.member.model.Member;
import com.diary.domain.post.model.Post;
import com.diary.domain.tag.model.Tag;
import com.diary.domain.tag.model.TagType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag,Long>, TagRepositoryCustom {
    List<Tag> findAllByPost(Post post);


}
