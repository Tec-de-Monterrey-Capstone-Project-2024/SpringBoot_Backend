package com.springboot.connectmate.services;

import java.util.Map;

public interface EmailService {
    void sendSimpleEmail(String toEmail, String subject, String body);

    void sendAlertEmail(String toEmail, String subject, String template, Map<String,Object> variables);
}
