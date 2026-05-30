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
    private String sessionKey = "IQoJb3JpZ2luX2VjEBYaCXVzLXdlc3QtMiJHMEUCIAwjMuYLWpcOkqz2rWpMN241CfFkoXJgrgPCExSFlj66AiEA9xWTopJHZvrWTgZ2a1D0P6saEz1r1rCW4QaqKh5isCEqwQII3///////////ARABGgw2ODM5OTkzNjE2NzEiDL6LQCrvSVR1h1pYHiqVAkUTHh+ZAuHlQkl0vtQSnMe24wf6ZCh+oyFrIDHfiSGNNeasRiNpUIYc4sKqyppNPgMV9tZsZ0XYyekFdqdQLJ2BREbaiERA//FKV71c6dnXqZeZCprH4dc3HA1w94BwzJsG2X+hq6ZCBBqDG/EaT7XuRIYfTfmmPntXbN3ibb3eVtJJx2WgHMfQLXU2X8AtSibLy9yibFTMSmMJ9XxMLdsBmdGcmiCqo62zES0HraPxNwHx1r0W0hDplbGjwTq332uFPF/GnsrUGHI+F80tAWrvoxtlpNLhp43BsxhoPcl3GmSppt9CDekjZ267SgVLPNuK7mGB+iYZZRacBxtLyyY8FyapH3TeBgfunoKAPQhkmnXTcWwwiczr0AY6nQFWEuPHcC3Alma+/tAkwB+rwkgutq7eFGxT52zpBJsSOQ6VTG85ZiUTnMZva2GLmUnUVXPu+ciD4WVX8/5SBORrfvDBcoX0FmE5t4i7Dm/J+eslfbD0ODfRhnock5eo9nyJVDT0fw0LBW7iobCnV/me97omfFvovUZOp8XclhNlzPk83GEVgFTYPDBvoyQRLSQCn+YHPc6lu8mwNkXT";

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
