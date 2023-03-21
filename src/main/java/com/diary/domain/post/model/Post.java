package com.diary.domain.post.model;

import com.diary.common.base.BaseEntity;
import com.diary.domain.member.model.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access =AccessLevel.PRIVATE)
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @Column(nullable = false)
    private String title;

    @Column(nullable = false, name = "begin_at")
    private LocalDateTime beginAt;

    @Column(nullable = false, name = "finish_at")
    private LocalDateTime finishAt;


}
