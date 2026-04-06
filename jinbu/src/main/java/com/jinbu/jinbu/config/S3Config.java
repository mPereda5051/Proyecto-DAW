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

    private String sessionKey = "IQoJb3JpZ2luX2VjEAUaCXVzLXdlc3QtMiJHMEUCIQDl54CZdFsvGVZD/NWjXcuAUwpuC694pqg5jWmKWZIrLgIgTSG6pgl/AUrbpZS8WW8W1AOax/vo8F9km1Z+MvT5yBMqwQIIzv//////////ARABGgw2ODM5OTkzNjE2NzEiDP8KCF0ri8NbXWypOyqVAqkCqePP5J+GT0ABOQa54+wpKgxm6N7VfG14MzcKquXmA9KIZGDT2vW2OgvKf1Dz8+BjoYExJxX3GYR3dNuFxwXHa9qI2DEZouLV8Uyqqc6tBY2Hz0bCn+plEcDFA+Hp9wpoBNBsz4RjolciOcCAmep56WSSRndYZFpqnCjXWqWJWBtKisSF9e5V7JXYSyIOJMq8+0O5TO7WGMABUi61Oz1ru92ugS2b+zhbl8EzFh51Zeer1USqrVAv7jT5gPDmZCbvx9n91aLnXRXQM3y6JySN9MSd+5Bd7nfS+fjIV0lHq288ND4JY/m6/onH1y3onoabExP77DzDoWjbNAnrnqg13f1zOM1DSioyxsqBZ3WZk3cgC2Mwk+TOzgY6nQEBy3Dp+MWTSodClWgaFjkmyYxNWVrkdLXQMO9FplYVoE1OnESyFP48qrQe/ZtdQvPTYyvrm99F3HAG5f8HQkoAM5eDE1k7tYo+X+UX/aA1GSIgVJbhvEZjgXJ5KlTEzxhbH55bS5g2lY1gXgxw+gHWKrjgmXzdUGmlL44nxc4fm7y8sT2pHRFB0evs2AP2srkC7J19fXLuaPaiN612";

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
