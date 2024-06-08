package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.SMS.SmsRequestDTO;
import com.springboot.connectmate.services.SnsService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Hidden
@RestController
public class SnsController {

    @Autowired
    private SnsService snsService;

    @PostMapping("/sendSms")
    public String sendSms(@RequestBody SmsRequestDTO smsRequestDTO) {
        snsService.sendSms(smsRequestDTO.getPhoneNumber(), smsRequestDTO.getMessage());
        return "SMS sent successfully!";
    }
}
