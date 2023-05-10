package com.diary.domain.post.model.dto;


import com.diary.domain.file.model.dto.GetFileResponse;
import com.diary.domain.tag.model.dto.GetTagResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private Map<String,String> experiences;
    private List<GetTagResponse> getTagResponses;
    private List<GetFileResponse> files;

    public static GetPostResponse of(Long postId, String title, LocalDateTime beginAt
        , LocalDateTime finishAt, Map<String,String> experiences, List<GetTagResponse> getTagResponses,
                                     List<GetFileResponse> files) {
        return new GetPostResponse(postId,title,beginAt,finishAt,experiences,getTagResponses,files);
    }
}
