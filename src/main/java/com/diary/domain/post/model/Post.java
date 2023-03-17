package com.diary.domain.post.model;

import com.diary.common.base.BaseEntity;
import com.diary.domain.member.model.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false, name = "begin_at")
    private LocalDateTime beginAt;

    @Column(nullable = false, name = "finish_at")
    private LocalDateTime finishAt;

}
