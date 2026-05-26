package com.jinbu.jinbu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {
    @Value(("${cloud.aws.credentials.access-key}"))
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    // Token session key
    private String sessionKey = "IQoJb3JpZ2luX2VjELb//////////wEaCXVzLXdlc3QtMiJIMEYCIQChXSS9MWHrMJdpyHqYe5okuerhCazNvdF/S9xg96f3jwIhAMPtmLxHBvWQ7Zz+qdqLwy4pPL6srgYhIt+nzc0Kd3kaKrgCCH8QARoMNjgzOTk5MzYxNjcxIgz1NPESXR7C4ZySO1wqlQJ+fU/6/ySrsL3mcgrsY3qQWW/HFFKduS7XmqRMosh1D9vip31I5ih0QaK5bQsgwYuAxYxmVExzglmhMtfVa7EIXgzOwglEf8zM4uXevjLoGUCqo4o0zh+ROuBLI7ct9KyjR7l6oMdqu/Vo4S/os8KQbPldOQbfOtRS9Rxuc0HsRYr0OxKPvRkyqh7cYu7AGmbCe1zZ8Xb5qNkqugxb3O5Iqt9+LqQuKYOGIkR4PvvaKM7GPR4oURiSM8Efm3WSjRYpwa5yUlqA4uu8cqaZCvSlgY4sg3RrbwRgyGFHkk5Lji+u32jNz3ed+e9U2C5VN6QkLpWrud8Fr2OTGdeFUavPJJtvnvHraWwvrpnENWrbUbgzP50eMJO+1tAGOpwBOGBsgeLFveWfbv9qwBwQ9pTUq7hrn/xC11I4gnmI2jawKowaSIdxuv+NdFR/8PjmRd9SYAf5/l7ztL7z24bWT4cnSE/XPU5oTvXC58hwJ6v2TmeR67dIChFTHEE7zsI7hDXkBpQSMtbJAY983+EiyN6CcX3o/NTCP+NP3iUop7h0uen75FwK7j7+fZp3MSuYKfyG5YD06d42eTIs";

    @Bean
    public S3Client s3Client() {
        // Cambio del bassic para poder usar la sessionKey de AWS academy
        AwsSessionCredentials awsSessionCredentials = AwsSessionCredentials.create(accessKey, secretKey, sessionKey);
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsSessionCredentials))
                .build();
    }
}
