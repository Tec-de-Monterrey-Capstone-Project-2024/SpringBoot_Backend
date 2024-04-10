package com.springboot.connectmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods based on the fields
@Schema(
        name = "Agent",
        description = "DTO for Agent"
)
public class AgentDTO {

    @Schema(
            description = "Id of the Agent",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Name of the Agent",
            example = "Jane Doe"
    )
    private String name;

    // TO DO: Omit email as well ¿?
    @Schema(
            description = "Email of the Agent",
            example = "agent@example.com"
    )
    private String email;
  
    // TO DO: Omit password
    @Schema(
            description = "Password of the Agent",
            example = "password"
    )
    private String password;

    @Schema(
            description = "Total calls handled by the Agent",
            example = "500"
    )
    private Integer totalCallsHandled;

    @Schema(
            description = "Average handling time (AHT) in seconds",
            example = "300"
    )
    private Integer averageHandlingTime;

    @Schema(
            description = "Customer satisfaction score (CSAT), from 1 to 5",
            example = "4"
    )
    private Integer customerSatisfactionScore;

    @Schema(
            description = "First Call Resolution (FCR) rate, as a percentage",
            example = "75"
    )
    private Integer firstCallResolutionRate;

    public enum AvailabilityStatus {
        AVAILABLE, BUSY, OFFLINE, ON_BREAK
    }
    @Schema(
            description = "Availability status of the Agent",
            example = "AVAILABLE"
    )
    private AvailabilityStatus availabilityStatus;
}
