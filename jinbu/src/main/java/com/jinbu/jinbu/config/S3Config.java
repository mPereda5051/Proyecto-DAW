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
    private String sessionKey = "IQoJb3JpZ2luX2VjEOH//////////wEaCXVzLXdlc3QtMiJHMEUCIQCHwrn6D6sIyMi65JpifaBwJLlwms6hsJWrPQJQxAnNeAIgUs49mJK36PaztQ+bs5Q39Rkcb7VDuiAaPF0+xUHHvS0qwQIIqv//////////ARABGgw2ODM5OTkzNjE2NzEiDHY4OvcqossWX+pojyqVAun0p7UcHFFvK65j7OBrisUMjZbKI2FZL5N+LRbd2HzJnfBEl6EFkAjy8BaZKJ3QVhKOZoAlXzFmwxeSxcVbW9l05obtoIxtntt3Cs7cr9ocGYfE5Z42EVaJ8Guws3Wdn6YfSD57aUiFYUnun7z9Is+9FbChXEQnXSuqZbUYVHDALcd/loNA3adoXxvAYlZCwnaCJwzbV7wvpDT08SrHZ1tHXTPxVPjz5nQbu/otSCHqshRpe9s9jECTmVpDQ/NuqSoWQCxToyKmWB1PGL3B/tlrE9AjNmPlRL55fdnzvbiEsB5RObC85IXPHifYRVw5YPPdu6q+u31BYuW4SujN/JReC0/F6N/jnEVNKTQYx6X2vD2C2Uswu4/g0AY6nQGlcM9XQhINl9Cp1fGZAwl8Ycyu1GLYVVk6njJyXwcyUjc5bJoupF6ysERySlcxDO9OCkQQarSQHnpPAdJT7Fnulkp/wVMZcbbL/PjxJ9yMiMJROAm2hS0vQ5+iOdoLFUDOgeZ3yMrZpilrqTy06hKTf8sxzpYrWi32DWgfk5w9IyGGurjTQtv6q0Z6qW6lKHiE/C5EjCLZeUtQ9Wlr";

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
