package com.springboot.connectmate.dtos.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "UpdateUserDTO",
        description = "DTO for Updating User"
)
public class UpdateUserDTO {

    @Schema(
            description = "First name of the user",
            example = "John"
    )
    private String firstName;

    @Schema(
            description = "Last name of the user",
            example = "Doe"
    )
    private String lastName;

    @Schema(
            description = "New password for the user",
            example = "newPassword123"
    )
    private String password;
}