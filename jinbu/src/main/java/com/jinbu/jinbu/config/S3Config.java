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
    private String sessionKey = "IQoJb3JpZ2luX2VjEIf//////////wEaCXVzLXdlc3QtMiJIMEYCIQDVq99aqRy65mpsxKWdj+3KgKHdk0ycmeXfXAPESKGbdAIhALIW+4zxNxjWwM25HHeS+2Z7ExAco5cXX0eOhDtv1U8JKrgCCFAQARoMNjgzOTk5MzYxNjcxIgzltW4p/IW+o9InI44qlQIMUGlXWekS1nk4XdgWSJ+cOH/TJOiTi6UgGuxgTh4Uj4J/6vIh2aQzSo2atWX0tvXiN8jvQvccXGNihBgxHcpx+/s3m0EsWlGvRdYZfj/SszPEnxoSbA+EfZ1x2AuHgHz0+/XHnzXz9gMd4kDH+cki9OTEQ4jPzn4pXRQQPm3uAy0HMbfslnyLb1eVw6IZkM4Sf5Zdc8HZcgtQq/XgCZokoCKOJKkUW9bsBK5twJvlm3Ggz+D24qTOK7/ZHrwHFCeTCC4AdQ/lZhOOwT1Cl0Qb9lf8RgYFFy3XvmjRtGlBrDQrbe5rE9rm6vKdct2wU0tcEIY2DjUsrHVtK47K8yTC5Q77xAFVnaPf3rJ/4x+rRXxwDBgZMN+rzNAGOpwBNeGQ/hLzoUSOB1lIKyIs5Wv+HQB+GFl0E1TO5jWBTIVJ9vnUrCk9gBnevV0Sn8g2Al50TtoKnfqIg/ncO++pV0JC68aOXdzSYz6Nb2ZAZqfNhytoBELpjOXiqb1fzmLyXZK8f6TTSqL1yrmKcM6V/njf+M1MHSTkDnQhqUfRhFEyqLy7AwC/50POVmj5hAXKELtO/p49UpxmvoBY";

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
