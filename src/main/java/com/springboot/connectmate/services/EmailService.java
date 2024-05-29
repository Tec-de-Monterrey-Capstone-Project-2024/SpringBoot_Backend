package com.springboot.connectmate.services;

public interface EmailService {
    void sendSimpleEmail(String toEmail, String subject, String body);

}
