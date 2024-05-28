package com.springboot.connectmate.dtos.SMS;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "SMSRequestDTO",
        description = "Data Transfer Object (DTO) for Request Body for send SMS messages"
)
public class SMSRequestDTO {
    private String message;
    private String phoneNumber;
}
