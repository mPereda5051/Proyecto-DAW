package com.jinbu.jinbu.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Component
public class    S3ImageStorage {

    @Autowired
    private S3Client s3Client;

    @Value("${aws.bucket.name}")
    private String bucketName;

    public void store(MultipartFile file, Long id) throws IOException {
        String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        s3Client.putObject(PutObjectRequest.builder()
                .bucket(bucketName)
                .key(id.toString() + fileExtension)
                .build(),
                // Usamos inputStream para ir pasando el file poco a poco (al contrario que con fromBytes)
                RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
    }

    public byte[] download(String key) {
        ResponseBytes<GetObjectResponse> objectAsBytes = s3Client.getObjectAsBytes(GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build());
        return objectAsBytes.asByteArray();
    }
}
