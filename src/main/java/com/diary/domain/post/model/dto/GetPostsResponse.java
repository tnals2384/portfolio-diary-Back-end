package com.diary.domain.post.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetPostsResponse {

    private Long postId;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime beginAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime finishAt;

    private List<String> tagName;

    public static GetPostsResponse of(Long postId, String title, LocalDateTime beginAt, LocalDateTime finishAt, List<String> tagName) {
        return new GetPostsResponse(postId, title, beginAt, finishAt, tagName);
    }

    public GetPostsResponse updateTagName(List<String> tagName){
        this.tagName = tagName;
        return this;
    }
}
