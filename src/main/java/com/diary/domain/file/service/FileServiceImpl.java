package com.diary.domain.file.service;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.diary.common.exception.ErrorCode;
import com.diary.common.exception.RestApiException;
import com.diary.domain.file.model.File;
import com.diary.domain.file.model.dto.UploadFileResponse;
import com.diary.domain.file.repository.FileRepository;
import com.diary.domain.post.model.Post;
import com.diary.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final FileRepository fileRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public UploadFileResponse uploadFiles(Long postId, List<MultipartFile> files) throws IOException {

        List<Long> fileList = new ArrayList<>();

        Post post = postRepository.findById(postId).orElseThrow(() -> new RestApiException(ErrorCode.NOT_FOUND));


        for (MultipartFile f : files) {

            String fileName = "files" + "/" + createFileName(f.getOriginalFilename());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(f.getContentType());

            //s3에 file 업로드
            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, f.getInputStream(), objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            //저장된 파일 경로 url
            String url = amazonS3Client.getUrl(bucket, fileName).toString();

            //File 생성
            File newFile = File.newFile(post,
                    f.getOriginalFilename(), url);

            //file repository에 save
            fileList.add(fileRepository.save(newFile).getId());

        }

        return UploadFileResponse.of(fileList);
    }


    //파일 이름 생성
    private String createFileName(String origFileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(origFileName));
    }


    //파일 확장자 get
    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("잘못된 형식의 파일 (%s) 입니다.", fileName));
        }
    }


}
