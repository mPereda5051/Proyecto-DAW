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
    private String sessionKey = "IQoJb3JpZ2luX2VjEOz//////////wEaCXVzLXdlc3QtMiJGMEQCIGhiaZNVLsB9Uq+aJRPdwOPQAUN76c3Ns6/DaAT+tVhUAiAmQZRbA7BX6AZzVniwzOQFeFYDUaG9hYSdeFe78ZPfkirBAgi1//////////8BEAEaDDY4Mzk5OTM2MTY3MSIMtSbZvX/Y5exoDh47KpUCQc0ppcjBD4CfcN9bigTD3u+/aFurUU/zjjFYxL66dqB3Sij+5ZTgrum/u2b0QX6u1gzwbx1XppqJY7Gbe2zvjv4HpiQ282uFx+jFWJeAZUO5DHlBvQ5p17TipN/eClNPPKfX5x5iAHCBfaDnxJmG4af0Lfr8rCOS5kEXPeyt2PbzXuMoGY6/+crjkkNqGVu5m8XaUiEVkP+s/vfuYlEs5iQbhI8W1xI4uMpBX0MO/bGYjz/rB2vmtGd9CfQy4CpCRyiNlePdfD9Ygv4tJpMyU1mt9hZmzjj7Lv3UXcC3BD9HckD7lLTuPGi5l+HHzZvnXpD4qWmbIjGzjf124N6I3uY+81nsOqGJ5jBqNv7ss3ZkBcWxKjCKsuLQBjqeAYf47BXB+EVXX0XNUEKBn2MlPYjAC0lKRFFfF2PFxY9YImacim8Y3Er1yxVSrXAjDj5nkmAu5xyn3rSIGRI6OK9H4d/rWW92VnOpzJnA8VvVRbJtz5NB1BcpK6KsS1hm46uqU+IjW7IJ56hkydJxqoU9FNVAjDj7F71OA7sVFnRM1NuyU/ZbJd+XyDqpJgFRF18O1QV4Ihyxw75stNtt\n";

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
