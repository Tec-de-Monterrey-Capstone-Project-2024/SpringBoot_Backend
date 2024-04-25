package com.springboot.connectmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for Agent Metrics")
public class AgentMetricsDTO {
    @Schema(description = "Service level percentage")
    private double serviceLevel;

    @Schema(description = "Occupancy rate percentage")
    private double occupancy;

    @Schema(description = "Schedule adherence percentage")
    private double scheduleAdherence;

    @Schema(description = "Number of abandoned calls")
    private double abandonedCalls;

    @Schema(description = "First call resolution rate percentage")
    private double firstCallResolution;

    @Schema(description = "Average answer speed in seconds")
    private double averageAnswerSpeed;
}
