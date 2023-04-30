package com.diary.domain.post.model.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetPostResponse {
    private Long postId;
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime beginAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime finishAt;

    //title, content
    private Map<String,String> experiences;
    //tagType, tag이름
    private Map<String,String> tags;
    //file이름, fileUrl
    private Map<String,String> files;

    public static GetPostResponse of(Long postId,String title, LocalDateTime beginAt
        ,LocalDateTime finishAt, Map<String,String> experiences, Map<String,String> tags,Map<String,String> files) {
        return new GetPostResponse(postId,title,beginAt,finishAt,experiences,tags,files);
    }
}
