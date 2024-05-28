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
    @Schema(
            description = "The content of message to be sent",
            example = "This is a simple email sent using Spring Boot."
    )
    private String message;

    @Schema(
            description = "The phone number of the receiver",
            example = "55 4884 5612"
    )
    private String phoneNumber;
}
