package com.springboot.connectmate.services;

public interface SnsService {
    void sendSms(String phoneNumber, String message);
}
