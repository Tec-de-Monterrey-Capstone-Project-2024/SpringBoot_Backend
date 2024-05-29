package com.springboot.connectmate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class AwsSnsConfig {

    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder()
                .region(Region.US_EAST_2) 
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
    }

    @Bean
    public SesClient sesClient() {
        return SesClient.builder()
                .region(Region.US_EAST_2) 
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
    }
}
