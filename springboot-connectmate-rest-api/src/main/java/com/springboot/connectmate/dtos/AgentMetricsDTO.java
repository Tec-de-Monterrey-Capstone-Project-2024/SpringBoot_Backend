package com.springboot.connectmate.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods based on the fields
@Schema(
        name = "Agent Metrics",
        description = "DTO for Agent Metrics"
)
public class AgentMetricsDTO {
    @Schema(
            description = "Id of the Alert",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Name of the Alert",
            example = "Service Level"
    )
    private String name;

    @Schema(
            description = "Minimum threshold for the Alert",
            example = "3"
    )
    private int minThreshold;

    @Schema(
            description = "Maximum threshold for the Alert",
            example = "5"
    )
    private int maxThreshold;

    @Schema(
            description = "Date of the metric",
            example = "2021-12-31T23:59:59"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime metricDate;
}
