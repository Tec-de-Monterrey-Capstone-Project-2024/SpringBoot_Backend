package com.springboot.connectmate.dtos.User;

import com.springboot.connectmate.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "UserInfoDTO",
        description = "DTO for User Information"
)
public class UserInfoDTO {

    @Schema(
            description = "Id of the User",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Name of the User",
            example = "John"
    )
    private String firstName;

    @Schema(
            description = "Name of the User",
            example = "John Doe"
    )
    private String lastName;

    @Schema(
            description = "Username of the User",
            example = "SuperJohn13"
    )
    private String username;

    @Schema(
            description = "Email of the User",
            example = "john@example.com"
    )
    private String email;

    @Schema(
            description = "Role of the User",
            example = "SUPERVISOR"
    )
    private UserRole role;
}
