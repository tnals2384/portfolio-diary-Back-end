package com.diary.domain.file.model;

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
public class File extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    /*@Column(nullable = false)
    private String url;*/

    @Column(nullable = false)
    private String origFileName;  // 파일 원본명

    @Column(nullable = false)
    private String filePath;  // 파일 저장 경로

    private Long fileSize;

    @Builder(access = AccessLevel.PRIVATE)
    private File(Post post, String origFileName, String filePath) {
        this.post = post;
        this.origFileName = origFileName;
        this.filePath = filePath;
    }

    public static  File newFile(Post post, String origFileName, String filePath) {
        return File.builder()
                .post(post)
                .origFileName(origFileName)
                .filePath(filePath).build();
    }

}
