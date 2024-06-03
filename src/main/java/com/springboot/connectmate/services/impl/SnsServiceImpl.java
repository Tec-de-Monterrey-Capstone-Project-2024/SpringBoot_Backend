package com.springboot.connectmate.services.impl;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.springboot.connectmate.services.SnsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnsServiceImpl implements SnsService {

    private static final Logger logger = LoggerFactory.getLogger(SnsServiceImpl.class);

    @Autowired
    private AmazonSNSClient snsClient;

    @Override
    public void sendSms(String phoneNumber, String message) {
        PublishRequest request = new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phoneNumber);
        PublishResult result = snsClient.publish(request);
        logger.info("SMS sent with messageId: {}", result.getMessageId());
    }
}
