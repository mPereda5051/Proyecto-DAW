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
    private String sessionKey = "IQoJb3JpZ2luX2VjEP///////////wEaCXVzLXdlc3QtMiJGMEQCIGRSawlEn3bIEXrJZSdpPU1jjEEA72LhrpB33pvTIJO2AiAC0Ht4HfooDCvakXs8RNbf0ffsJ5/8Jbhj3c2Rg5kTmyrBAgjI//////////8BEAEaDDY4Mzk5OTM2MTY3MSIMbPUtR5FjzGMcOVzPKpUC1yec/dMZcOM+8LW3/oOopalmWPl3a8q7de/6HdG5/3sPaJJQSsIe0TbR/Uw0jM4G2q/3wn6wJn6pVFfhKk6yjgl237kImm2UC0c6+SfzY0oUfZIIa3kR2Ex3+5mVTgz/iQYi+rnKQbxMZvPHf66Zhq/sQC4kYh10DCBKuRADfE4NFrcSGKipRVArTDC7g9Hz8/OYpJ2D6mtMOOsr57Gkx6GLgn+EHjs/V0sQtS2UuPcnyh1z6Sck0wYINRThANGWq5upTa+DXo4GZc6CGsv+5qwQJxL3FfLrS7G7PLxH5UJfu7XGfybNd45DtdDF7/FN4aMjlcwdOeNIPCiOMxXpVGPoj2IbdzYxdWFeKHOVrm/q507vaDDNzebQBjqeAaxi7GYb/H4Uhalw9/f1ZA6NlEp6ep23pTeadd1e/rLLmfBEiI13+Zc3CNFB50EhDDLQuLd/RXF8qxbNanLLqIhq7rzT53LHkB2O2Uqn8gsiivErRB9OO1RVWIX+sVMwAlnPKcmaawQ6Wso9G+ZOeZPTxoTAfqL4pqi0DZtxke0FROUsTuonRG5Izq1EKz7yqfLHdRg2+8anYU02zaZU";

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
