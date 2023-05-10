package com.diary.domain.post.model.dto;


import com.diary.domain.experience.model.dto.GetExperienceResponse;
import com.diary.domain.file.model.dto.GetFileResponse;
import com.diary.domain.tag.model.dto.FindTagResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    private List<GetExperienceResponse> getExperienceResponses;
    private List<FindTagResponse> getTagResponses;
    private List<GetFileResponse> getFileResponses;

    public static GetPostResponse of(Long postId, String title, LocalDateTime beginAt
            , LocalDateTime finishAt, List<GetExperienceResponse> experiences, List<FindTagResponse> tags,
                                     List<GetFileResponse> files) {
        return new GetPostResponse(postId, title, beginAt, finishAt, experiences, tags, files);
    }
}
