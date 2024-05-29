package com.springboot.connectmate.dtos.AmazonConnect;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "ConnectSecurityProfileDTO",
        description = "DTO for a Security Profile in Amazon Connect"
)
public class ConnectSecurityProfileDTO {

    @Schema(name = "id", description = "The ID of the role")
    private String id;

    @Schema(name = "name", description = "The name of the role")
    private String name;
}