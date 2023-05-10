package com.diary.domain.memberTag.model;

import com.diary.common.base.BaseEntity;
import com.diary.domain.member.model.Member;
import com.diary.domain.tag.model.Tag;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Builder(access = AccessLevel.PRIVATE)
    private MemberTag(Member member, Tag tag){
        this.member = member;
        this.tag = tag;
    }

    public static MemberTag of(Member member, Tag tag){
        return new MemberTag(member, tag);
    }

}
