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
    private String sessionKey = "IQoJb3JpZ2luX2VjENH//////////wEaCXVzLXdlc3QtMiJGMEQCIDeIN2sDqiUKXtbBWXi1lzB320PC1yGb99E8KCiYdSzkAiAaz6ykuJ46E4rFlnQvIyoGf9QbIcwQHSllNWsUB0hqTirBAgia//////////8BEAEaDDY4Mzk5OTM2MTY3MSIM/o4Ug1H7SlmKiFkcKpUCFqnMt2kgAJJzf9oFYayBARbYZNkbhG3was7mx/OOsLMTw0U7tPtRLuRkszLlY3VrDdkddOrYh0aohzV7chQItsh5JA7PMXKJgQ9ylD2EkQIORyo5x+2/l00BqnqAe9NSwOSyiSe4iQQUf6B8ycbN2IyYFt19xYwYY+WN2w10lDbhE92q44QOdOyx0GXhtd9/UAYvH05fNuptDGzlv2ziXUsClIC3/+fpHyL8vPWbCNOeyW7VqVw3ZZtFrsiqUO85AAtYaek+0JCx7DDv1RZ4ojoDcHIjTR6mzN5iTZBCVzv4vdqYgw3muv1c01u+z0T0zPp3OLvfk7OU1K2QPQVm5Hqg54u8gKILaA7FoeYHo6zcAKV5NTDTwtzQBjqeATdPHLf8azD0Fa96vlrKp9eeb21boPTDzPdem/kRsc7iA79Z+SgxqjhqeoRKWZI2sj3de1cAhIQD1QC1meu7K/V08ymo1k6U0mVVrpc1Fgk5wkyHLYsh+qmWRLv39pc/HhFHwEoYE3uwkwVE5d5wWV5WZiQR6IK1HxGsaywdpYkf10HLcCYOOixIhz0EiDUGIE3BAip7PFlOBseTf8yo";

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
