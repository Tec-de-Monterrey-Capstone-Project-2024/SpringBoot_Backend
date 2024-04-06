package com.springboot.connectmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods for us
@Schema(
        name = "Agent",
        description = "DTO for Agent"

)
public class AgentDTO {
    private Long id;
    @Schema(
            description = "Name of the Agent",
            example = "John Doe"
    )
    private String name;
    @Schema(
            description = "Email of the Agent",
            example = "agent@example.com"
    )
    private String email;
    // TO DO Password is intentionally omitted to not expose it through DTO
    @Schema(
            description = "Password of the Agent",
            example = "password"
    )
    private String password;
}
