package com.springboot.connectmate.controllers;

import com.springboot.connectmate.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/sendEmail")
    public void sendEmail() {
        String toEmail = "Jose.aram.mendez@gmail.com";
        String subject = "Alert Notification";
        String template = "alert-template"; // This corresponds to alert-template.html
        //To make any changes or to add variables we need to do it in a Map
        //Depending on what variables are on the template, we pass it in the map
        //For testing im using sendEmail endpoint
        Map<String, Object> variables = new HashMap<>();
        variables.put("title", "Urgent Alert"); //Dummmy variables for testing
        variables.put("message", "This is an urgent alert message. (Falta mejorar el css)");

        emailService.sendAlertEmail(toEmail, subject, template, variables);
    }
}
