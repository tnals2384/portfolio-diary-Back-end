package com.diary.domain.tag.model;

import com.diary.common.base.BaseEntity;
import com.diary.domain.post.model.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Enumerated(EnumType.STRING)
    private TagType tagType;

    @Column(nullable = false)
    private String tagName;

   @Builder(access = AccessLevel.PRIVATE)
    private Tag(TagType tagType,String tagName, Post post) {
       this.tagType=tagType;
       this.tagName= tagName;
       this.post=post;
   }

   public static  Tag newTag(TagType tagType,String tagName, Post post) {
       return Tag.builder()
               .tagType(tagType)
               .tagName(tagName)
               .post(post)
               .build();
   }

}
