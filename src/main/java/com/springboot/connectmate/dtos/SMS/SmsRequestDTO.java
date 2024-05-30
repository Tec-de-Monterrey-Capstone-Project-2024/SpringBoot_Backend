package com.springboot.connectmate.dtos.SMS;

import lombok.Getter;

@Getter
public class SmsRequestDTO {

    private String phoneNumber;
    private String message;

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
