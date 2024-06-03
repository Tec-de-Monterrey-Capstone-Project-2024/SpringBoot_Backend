package com.springboot.connectmate.dtos.SMS;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "SmsRequestDTO",
        description = "DTO for sending SMS"
)
public class SmsRequestDTO {

    @Schema(
            description = "Phone number to send SMS",
            example = "1234567890"
    )
    private String phoneNumber;

    @Schema(
            description = "Message to send",
            example = "Hello, this is a test message"
    )
    private String message;

}
