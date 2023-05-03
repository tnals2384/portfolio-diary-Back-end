package com.diary.domain.post.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetPagePostsResponse {

    //한 페이지의 post 정보를 담은 GetPostsResponse의 List
    private List<GetPostsResponse> pagePosts;

    private int totalPages;
    private int totalPosts;

    public static GetPagePostsResponse of(List<GetPostsResponse> pagePosts,
                                          int totalPages, int totalPosts) {
        return new GetPagePostsResponse(pagePosts,totalPages, totalPosts);
    }
}
