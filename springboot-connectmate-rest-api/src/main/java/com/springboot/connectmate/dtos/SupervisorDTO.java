package com.springboot.connectmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods for us
@Schema(
        name = "Supervisor",
        description = "DTO for Supervisor"

)
public class SupervisorDTO {
    private Long id;
    @Schema(
            description = "Name of the Supervisor",
            example = "John Doe"
    )
    private String name;
    @Schema(
            description = "Email of the Supervisor",
            example = "supervisor@example.com"
    )
    private String email;
    // TO DO Password is intentionally omitted to not expose it through DTO
    @Schema(
            description = "Password of the Supervisor",
            example = "password"
    )
    private String password;
    @Schema(
            description = "Phone number of the Supervisor",
            example = "+1234567890"
    )
    private String phone;
    @Schema(
            description = "Address of the Supervisor",
            example = "1234 Main St, City, Country"
    )
    private String address;
    // TO DO Instance Id is intentionally omitted to not expose it through DTO
    @Schema(
            description = "Amazon Connect Instance Id",
            example = "12345678-1234-1234-1234-123456789012"
    )
    private String instanceId;
}
