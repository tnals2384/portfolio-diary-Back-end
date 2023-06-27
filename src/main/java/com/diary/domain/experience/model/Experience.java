package com.diary.domain.experience.model;

import com.diary.common.base.BaseEntity;
import com.diary.domain.post.model.Post;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private String content;


    @Builder(access = AccessLevel.PRIVATE)
    private Experience(String title, String content,Post post) {
        this.title = title;
        this.content = content;
        this.post = post;
    }

    public static Experience newExperience(String title, String content, Post post){
        return Experience.builder()
                .title(title)
                .content(content)
                .post(post)
                .build();
    }

    public void update(String title,String content) {
        this.title=title;
        this.content=content;
    }

}
