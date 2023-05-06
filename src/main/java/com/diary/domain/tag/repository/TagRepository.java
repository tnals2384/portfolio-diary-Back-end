package com.diary.domain.tag.repository;

import com.diary.domain.post.model.Post;
import com.diary.domain.tag.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long>, TagRepositoryCustom {
    List<Tag> findAllByPost(Post post);


}
