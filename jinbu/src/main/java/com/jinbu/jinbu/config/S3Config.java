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
    private String sessionKey = "IQoJb3JpZ2luX2VjEPr//////////wEaCXVzLXdlc3QtMiJHMEUCIHNCCmeiwOcLtkHjMkbzrIS5N5ma4l4sORm/Pogr2SNeAiEAu0wtOwm5CxF7s9W6BUX2+JuFupa2SQl5CO0nTiAp/x4qwQIIwv//////////ARABGgw2ODM5OTkzNjE2NzEiDLA+NlU3/9heZ8SO3SqVAk6v7jkvbMkHHwtwFf61yJaNcozNVz8ai7SzBieekbAeWAT6KYox4GqF52cj1xSK3MuQdXllqPuYtUfp4q9A+vSr2QzPsiPS+ytVmjfa2TzCCVQEn79qAcdHQW+2GHsaAMvVMZErzAuyrPtLe4A/oOCD6pIA1AGJJEtTBK9bxcqfITyMHiosgkoyVjwuIQttYhRarI+lrUvgs3xvkEUrt+8bXTxX2BmmVQ8SXo7k4PiSthW2So5HkBYUOQLyGCVyAj1M5M9vqUd5srTaUUklRQGBB6p2A7wVdv+QtK7jS19e60O7p9UdtthwQjdAcbSaIHBPASy0lUAN39aS3cZ987QMh+v98B/BKhbsyUAEUxG0rLnDQhEw07Pl0AY6nQFC6JGKBw4uYoI5FnQvwDgRqYUqm/9cMdAUVXAZ7i2GWNuGBzzzr6+KyFm0+Qeq+Qteuf2hIJG9iqN1UP0/rMQjj7nmoRuQhRNJI8j8vV7ALM5k9pXIookbUAcpm2hquz7nSmmYs3u3aJyb3pLPfFv2SNxgWtYtXB17WCojVh7W3Dve531GPbgX3DDlBnQgN3ek9oPADl9y/baggs/g\n";

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
