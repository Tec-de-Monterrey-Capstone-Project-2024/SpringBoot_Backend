package com.springboot.connectmate.dtos.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "UpdateUserRequestDTO",
        description = "Data Transfer Object for updating User"
)
public class UpdateUserRequestDTO {

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