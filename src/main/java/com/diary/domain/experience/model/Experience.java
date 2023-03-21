package com.diary.domain.experience.model;

import com.diary.common.base.BaseEntity;
import com.diary.domain.post.model.Post;
import lombok.*;

import javax.persistence.*;


@Builder
@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Experience extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experience_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;


}
