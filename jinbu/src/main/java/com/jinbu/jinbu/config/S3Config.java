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
    private String sessionKey = "IQoJb3JpZ2luX2VjEMr//////////wEaCXVzLXdlc3QtMiJHMEUCIDOYDzuihJju0o4vWnhobeiG0raGaieFC/2FNcwv329hAiEAr/RnlYEXc7BBxIwI8lRWkDE9Bp5Rog/OC5qTRcGfhN8qwQIIk///////////ARABGgw2ODM5OTkzNjE2NzEiDA2CcVC8KkmnK2iC5CqVAoP0MnFaW52Tj9YDnpJ0epFMVzl/qndVik4M4Z4mZEGRF2QaeFhoGGtPylPMtM2iYKXde8laDqXUzD/o2P4EzFqwxf6H0W+HeiETl8G1lFf4BeWTG2DsTXgF+Z4t5ROedC/amFLoBB4spqqp53seZdED+KOPqZbV6Iqqts0x5p90QG67/GUeub/fPs3YocJEIfgawPyxw7sBMGZqCrypAwgUBN4ruzJj9DwYbossXmKoYuDHQaRscwly8/rdCpWF6zgpoBMveshspYcefRlpFqUVigrXPdlUxx8hDCubGhlpX+Bsb7Y13MP+lSM187+hvG7qZIvVKra6ZZUnkrsuDLZDi4CXfD1EK6aEFq8/3GpN9VumI+Yw6/La0AY6nQGL8wPLMfsENkcOwx40X/CvZP3JDLVxKl05rkGiWpnNwHONAJY8RSkjlGvjBT5hT6C0CVp8P2QXlkzA46nwvD8d/SBY6iW7rLOo6PQXkHC82YRienc1qp4oyBEQR+0Gu1LPed751xnsgziAGc+d/+sCS47gLG6X8GhScFQ96s6hmbRYFmpdGVqJRSAd0yeXnEMolcSzZu7EXQ3/9iSr";

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
