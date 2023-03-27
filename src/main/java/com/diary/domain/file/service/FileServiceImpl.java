package com.diary.domain.file.service;

import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import com.diary.domain.file.model.dto.UploadFileResponse;
import com.diary.domain.file.repository.FileRepository;
import com.diary.domain.post.model.Post;
import com.diary.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final PostRepository postRepository;

    public UploadFileResponse uploadFiles(Long postId, List<MultipartFile> files) throws IOException {

        List<Long> fileList = new ArrayList<>();
        Post post = postRepository.findById(postId).orElseThrow(() -> new RestApiException(ErrorCode.NOT_FOUND));

        //오늘날짜 추출
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyyMMdd");
        String current_date = now.format(dateTimeFormatter);

        //파일 저장 절대 경로
        String absolutePath = new File("").getAbsolutePath()
                + File.separator + File.separator;

        //파일 세부 경로 지정
        String path = absolutePath + "files" + File.separator + current_date;

        //디렉토리가 존재하지 않으면 mkdirs로 생성
        if (!new File(path).exists()) {
            try {
                new File(path).mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        for (MultipartFile f : files) {
            // 파일의 확장자 추출
            String originalFileExtension;
            String contentType = StringUtils.getFilenameExtension((f.getOriginalFilename()));
            if (ObjectUtils.isEmpty(contentType))
                break;
            else
                originalFileExtension = "." + contentType;

            // 파일명 중복 피하고자 나노초까지 얻어와 지정
            String new_file_name = System.nanoTime() + originalFileExtension;

            com.diary.domain.file.model.File newFile =
                    com.diary.domain.file.model.File.newFile(post,
                            f.getOriginalFilename(),path+File.separator+new_file_name);

            //file repo에 save
            fileList.add(fileRepository.save(newFile).getId());

            //업로드 한 파일 데이터를 지정한 파일에 저장
            File file = new File(path + File.separator + new_file_name);
            f.transferTo(file);

            // 파일 권한 설정(쓰기, 읽기)
            file.setWritable(true);
            file.setReadable(true);
        }

        return UploadFileResponse.of(fileList);
    }

}
