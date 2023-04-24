package com.diary.domain.post.model.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeletePostResponse {
    private Long postId;

    public static DeletePostResponse of(Long postId) {

        return new DeletePostResponse(postId);
    }
}
