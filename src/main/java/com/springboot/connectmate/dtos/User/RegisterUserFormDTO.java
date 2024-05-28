package com.springboot.connectmate.dtos.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "RegisterUserFormDTO",
        description = "DTO for Register User Form"
)
public class RegisterUserFormDTO {

    @Schema(description = "Firebase ID of the user", example = "firebaseId123")
    @NotBlank(message = "Firebase ID cannot be blank")
    private String firebaseId;

    @Schema(description = "Primary email of the user", example = "a01657142@tec.mx")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(description = "Connect Instance ID of the AWS Account", example = "connectInstanceId123")
    @NotBlank(message = "Instance ID cannot be blank")
    private String instanceId;

}
