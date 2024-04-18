package com.springboot.connectmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AgentMetricsDTO {
    @Schema(description = "Service level")
    private double serviceLevel;

    @Schema(description = "Occupancy rate")
    private double occupancy;

    @Schema(description = "Schedule adherence rate")
    private double scheduleAdherence;

    @Schema(description = "Number of abandoned calls")
    private double abandonedCalls;

    @Schema(description = "First call resolution rate")
    private double firstCallResolution;

    @Schema(description = "Average answer speed")
    private double averageAnswerSpeed;

    // Constructor can be generated automatically by Lombok

    // Getter and setter methods are generated automatically by Lombok
}
