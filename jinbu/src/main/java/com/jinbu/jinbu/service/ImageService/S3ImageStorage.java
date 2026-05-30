package com.jinbu.jinbu.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Component
public class S3ImageStorage {

    @Autowired
    private S3Client s3Client;

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Value("${aws.bucket.name2}") //
    private String profilePicturesBucketName;

    public void store(MultipartFile file, Long id) throws IOException {
        String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        s3Client.putObject(PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(id.toString() + fileExtension)
                        .contentType(file.getContentType())
                        .build(),
                // Usamos inputStream para ir pasando el file poco a poco (al contrario que con fromBytes)
                RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
    }

    public void storeProfilePicture(MultipartFile file) throws IOException {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        s3Client.putObject(PutObjectRequest.builder()
                        .bucket(profilePicturesBucketName)
                        .key(username)
                        .contentType(file.getContentType())
                        .build(),
                // Usamos inputStream para ir pasando el file poco a poco (al contrario que con fromBytes)
                RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
    }
    
    public void delete(Long id, String extension) {
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(id.toString() + extension)
                .build());

    }

    public void deleteProfilePicture() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(profilePicturesBucketName)
                .key(username)
                .build());
    }

    public byte[] download(String key) {
        ResponseBytes<GetObjectResponse> objectAsBytes = s3Client.getObjectAsBytes(GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build());
        return objectAsBytes.asByteArray();
    }
}
