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
    private String sessionKey = "IQoJb3JpZ2luX2VjEBEaCXVzLXdlc3QtMiJIMEYCIQCf/QORp5+l/x5QiRfX8F9E2y2f/UBB0zd5UU/JhkX9zAIhAO9/AopUO44mhS/R4UwRYHj1EGmGNITMH2aHCZ+fW0uHKsECCNr//////////wEQARoMNjgzOTk5MzYxNjcxIgwiddj6VXMYOskPB98qlQJ4LcqK0NZMyHN2+DYTJmNpJOboa+X6d5jsNvB4NhdV8VgNrFvPATx2/n+wnyntNj/IMfQg4K2mkzTgJasYJOTL2456+ffmTi5iIjhOysUpMOBS+PFtOFyudAsNEHAQ7HafxQECcJCvciTtr+whz/VVwfQYV5B461tWhkoFXDJPQy8epsZRyb87BPisT0TOrydMefGN9JTMBvw2y9AIy2C77q/MjK1gYX+Dgpn42Ta14wobxTU/VkZQeliqRWBFtVCsIglIY06ycz48nc/t42aiOPFWkP4R/wT444rL089vM20+frkGrlKeUJBd5QsZ1EV5vHIIVJAYMnY1/c3I2Pc7ysj5be75QR4hzKdNp0IFd0x+ku/hMIbW6tAGOpwBXz6MD2kdEzey1NIWT9IDAIGzPEWKjefQKzHelToRO1gVhWfPCQTUZ+agGkUHDr8YVIQI2Bz5swAKHqramjEvkhCqMzYbrx6rnuxp05EMonN37sBBV9udgdCDQ3IXhblztRqJ6yBUw/OnX4ck/zhkbHCqcNV8/p0W7WYUWRSUoYTtXUuNqtcoOGPxARqn7EbgUD+pbfUXl8BN/MGB";

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
