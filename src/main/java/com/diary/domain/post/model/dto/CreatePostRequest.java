package com.diary.domain.post.model.dto;


import com.diary.domain.member.model.Member;
import com.diary.domain.post.model.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePostRequest {
    @NotBlank
    private String title;
    @NotNull
    private LocalDateTime beginAt;
    @NotNull
    private LocalDateTime finishAt;

    private Map<@NotEmpty String, @NotBlank String> tags;

    private Map<@NotEmpty String, @NotBlank String> experiences;


    public Post toEntity(Member member) {
        return Post.newPost(member, title, beginAt, finishAt);
    }
}
