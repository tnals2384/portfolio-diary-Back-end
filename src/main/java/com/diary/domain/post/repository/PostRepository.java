package com.diary.domain.post.repository;

<<<<<<< HEAD
import com.diary.domain.experience.model.Experience;
import com.diary.domain.member.model.Member;
=======
>>>>>>> upstream/dev
import com.diary.domain.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long>, PostRepositoryCustom {
    List<Post> findAllByMember(Member member);

}
