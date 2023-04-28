package com.diary.domain.file.repository;

import com.diary.domain.file.model.File;
import com.diary.domain.member.model.Member;
import com.diary.domain.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File,Long>, FileRepositoryCustom {

    List<File> findAllByPost(Post post);
}
