package com.springboot.connectmate.dtos.AmazonConnect;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "ConnectAgentMetricDTO",
        description = "Data Transfer Object (DTO) for agent metrics into a specified Amazon Connect instance"
)
public class ConnectAgentMetricDTO {
    @Schema(
            description = "Abandonment rate of the agent",
            example = "90"
    )
    private double abandonmentRate;
    @Schema(
            description = "Average Handle Time of the agent.",
            example = "20"
    )
    private double avgHandleTime;

    @Schema(
            description = "Average After Contact Work Time of the agent.",
            example = "135"
    )
    private double avgAfterContactWorkTime;

    @Schema(
            description = "Occupancy of the agent.",
            example = "120"
    )
    private double agentOccupancy;
}
