package com.diary.domain.experience.service;


import com.diary.domain.experience.model.dto.*;
import com.diary.domain.member.model.Member;
import com.diary.domain.post.model.Post;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ExperienceService {

    CreateExperiencesResponse createExperiences(Long postId, Map<String, String> experiences) throws IOException;
    CreateExperienceResponse createExperience(Member loginMember, Long postId, CreateExperienceRequest request) throws IOException;
    UpdateExperienceResponse updateExperience(Member loginMember, Long experienceId, UpdateExperienceRequest request) throws IOException;

    void deleteExperiences(Post post);

    DeleteExperienceResponse deleteExperience(Member loginMember, Long experienceId);
    void softDeleteExperiences(Post post);

    List<GetExperienceResponse> getExperiences(Post post);

    void hardDeleteExperiences(Post post);

    void updateExperienceActive(Post post);
}
