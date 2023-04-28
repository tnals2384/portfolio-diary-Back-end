package com.diary.domain.experience.repository;

import com.diary.domain.experience.model.Experience;
import com.diary.domain.file.model.File;
import com.diary.domain.member.model.Member;
import com.diary.domain.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience,Long>, ExperienceRepositoryCustom {
    List<Experience> findAllByPost(Post post);

}
