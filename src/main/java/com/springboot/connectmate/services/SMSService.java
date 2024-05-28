package com.springboot.connectmate.services;

public interface SMSService {
    public String sendSms(String message, String phoneNumber);
}
