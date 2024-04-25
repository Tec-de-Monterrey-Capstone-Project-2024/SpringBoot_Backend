package com.springboot.connectmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods for us
@Schema(
        name = "Supervisor",
        description = "DTO for Supervisor"

)
public class SupervisorDTO {

    @Schema(
            description = "Id of the Supervisor",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Name of the Supervisor",
            example = "John"
    )
    private String firstName;

    @Schema(
            description = "Name of the Supervisor",
            example = "John Doe"
    )
    private String lastName;

    @Schema(
            description = "Email of the Supervisor",
            example = "supervisor@example.com"
    )
    private String email;

    // TO DO: Password is intentionally omitted to not expose it through DTO
    @Schema(
            description = "Password of the Supervisor",
            example = "password"
    )
    private String password;
}
