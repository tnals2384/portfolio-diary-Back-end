package com.diary.domain.post.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetPostsResponse {

    private Long postId;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime beginAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime finishAt;

    //tagtype, tag이름
    private Map<String,String> tags;

    public static GetPostsResponse of(Long postId, String title, LocalDateTime beginAt, LocalDateTime finishAt
    , Map<String,String> tags) {
        return new GetPostsResponse(postId,title,beginAt,finishAt,tags);
    }
}
