package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.SMS.SMSRequestDTO;
import com.springboot.connectmate.services.SMSService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class SMSController {
    private final SMSService smsService;

    public SMSController(SMSService snsService) {
        this.smsService = snsService;
    }

    @PostMapping("/sendSms")
    public ResponseEntity<String> sendSms(@RequestBody SMSRequestDTO smsRequestDto) {
        String messageId = smsService.sendSms(smsRequestDto.getMessage(), smsRequestDto.getPhoneNumber());
        return ResponseEntity.ok("Message sent with ID: " + messageId);
    }
}
