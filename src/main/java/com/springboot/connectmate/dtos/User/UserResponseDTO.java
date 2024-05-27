package com.springboot.connectmate.dtos.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "UserResponseDTO",
        description = "DTO for User Response"
)
public class UserResponseDTO {

    @Schema(description = "Connect ID of the user", example = "connectId456")
    private String connectId;

    @Schema(description = "Connect Instance ID of the AWS Account", example = "connectInstanceId123")
    private String instanceId;

    @Schema(description = "Username of the user", example = "aram_connectmate")
    private String username;

    @Schema(description = "First name of the user", example = "José Aram")
    private String firstName;

    @Schema(description = "Last name of the user", example = "Méndez Gómez")
    private String lastName;

    @Schema(description = "Primary email of the user", example = "a01657142@tec.mx")
    private String email;

    @Schema(description = "Secondary email of the user", example = "jose.aram.mendez@gmail.com")
    private String secondaryEmail;

    @Schema(description = "Mobile phone number of the user", example = "+523525231067")
    private String mobile;
}
