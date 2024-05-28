package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.services.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;


@Service
public class SMSServiceImpl implements SMSService {

    private final SnsClient snsClient;

    @Autowired
    public SMSServiceImpl(SnsClient snsClient){
        this.snsClient = snsClient;
    }

    public String sendSms(String message, String phoneNumber) {
        try {
            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .phoneNumber(phoneNumber)
                    .build();
            PublishResponse result = snsClient.publish(request);
            System.out.println("SMS sent successfully with message ID: " + result.messageId());
            return result.messageId();
        } catch (SnsException e) {
            System.err.println("Failed to send SMS: " + e.awsErrorDetails().errorMessage());
            return null;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            return null;
        }
    }
}
