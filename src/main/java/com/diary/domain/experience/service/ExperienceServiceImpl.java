package com.diary.domain.experience.service;

import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import com.diary.domain.experience.model.Experience;
import com.diary.domain.experience.model.dto.CreateExperienceResponse;
import com.diary.domain.experience.model.dto.UpdateExperienceRequest;
import com.diary.domain.experience.repository.ExperienceRepository;
import com.diary.domain.post.model.Post;
import com.diary.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {
    private final ExperienceRepository experienceRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public CreateExperienceResponse createExperiences(Long postId, Map<String, String> experiences) throws IOException {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RestApiException(ErrorCode.NOT_FOUND));

        List<Long> exList = new LinkedList<>();

        for (Map.Entry<String, String> ex : experiences.entrySet()) {
            Experience experience = experienceRepository.save(
                    Experience.newExperience(ex.getKey(),ex.getValue(),post));
            exList.add(experience.getId());
        }
        return CreateExperienceResponse.of(exList);
    }


    @Override
    @Transactional
    public void updateExperiences(List<UpdateExperienceRequest> requests) throws IOException{

       List<Experience> experienceList = experienceRepository.findAll();

       //Experience List와 Request List id를 비교하여 delete 해야 할 list 찾기
       List<Experience> deleteList = experienceList.stream().filter(o-> requests.stream().noneMatch(
               n -> o.getId().equals(n.getId())
       )).collect(Collectors.toList());

       //delete 로직
        if(!deleteList.isEmpty()) {
            experienceRepository.deleteAll(deleteList);
        }

        for(UpdateExperienceRequest request: requests) {
            Experience experience = experienceRepository.findById(request.getId()).orElseThrow(
                    ()-> new RestApiException(ErrorCode.NOT_FOUND)
            );
            experience.update(request.getTitle(),request.getContents());
        }
    }

    //experience 삭제
    @Override
    @Transactional
    public void deleteExperiences(Post post) {
        List<Experience> experiences = experienceRepository.findAllByPost(post);
        if(!CollectionUtils.isEmpty(experiences)) {
            experienceRepository.deleteAll(experiences);
        }
    }


    //getPost 시 Map<String(제목), String(내용)>으로 experiences get 가능하도록 함
    @Override
    public Map<String, String> getExperiences(Post post) {
        List<Experience> experiences = experienceRepository.findAllByPost(post);
        Map<String,String> responseExperiences = new HashMap<>();

        if(!CollectionUtils.isEmpty(experiences)) {
            for(Experience ex: experiences) {
                responseExperiences.put(ex.getTitle(),ex.getContents());
            }
        }
        return responseExperiences;
    }


}
