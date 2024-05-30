package com.springboot.connectmate.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Bean
    public AmazonSNSClient snsClient() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAZ2DJB76EVEVWCQLZ", "lUUh+6KiYzcxb2xbxqk0FmfHV39wnnP7CQ4HCBYW");
        return (AmazonSNSClient) AmazonSNSClientBuilder.standard()
                .withRegion("us-east-1")
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }
}

