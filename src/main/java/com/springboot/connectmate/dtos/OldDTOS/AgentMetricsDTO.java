package com.springboot.connectmate.dtos.OldDTOS;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods based on the fields
@Schema(
        name = "Agent Metrics",
        description = "DTO for Agent Metrics"
)
public class AgentMetricsDTO {
}
