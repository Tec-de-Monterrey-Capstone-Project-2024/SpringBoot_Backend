package com.springboot.connectmate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationPropertiesLoggerConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String serverPort;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    @Value("${spring.datasource.password}")
    private String datasourcePassword;

    @Value("${aws.accessKeyId}")
    private String awsAccessKeyId;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    @Value("${aws.region}")
    private String awsRegion;

    @Bean
    public CommandLineRunner logProperties() {
        return args -> {
            System.out.println("Application Name: " + applicationName);
            System.out.println("Server Port: " + serverPort);
            System.out.println("Datasource URL: " + datasourceUrl);
            System.out.println("Datasource Username: " + datasourceUsername);
            // Masking sensitive information
            System.out.println("Datasource Password: " + maskSensitiveInfo(datasourcePassword));
            System.out.println("AWS Access Key ID: " + maskSensitiveInfo(awsAccessKeyId));
            System.out.println("AWS Secret Key: " + maskSensitiveInfo(awsSecretKey));
            System.out.println("AWS Region: " + awsRegion);
        };
    }

    private String maskSensitiveInfo(String sensitiveInfo) {
        if (sensitiveInfo == null || sensitiveInfo.length() < 4) {
            return "****";
        }
        return sensitiveInfo.substring(0, 4) + "****";
    }
}

