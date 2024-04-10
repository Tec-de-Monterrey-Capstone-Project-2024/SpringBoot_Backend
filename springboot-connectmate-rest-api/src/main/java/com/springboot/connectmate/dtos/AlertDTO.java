package com.springboot.connectmate.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods based on the fields
@Schema(
        name = "Alert",
        description = "DTO for Alert"
)
public class AlertDTO {

    private static severity severity;

    @Schema(
            description = "Id of the Alert",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Id of the Metric associated with the Alert",
            example = "1"
    )
    private Long metricID;

    @Schema(
            description = "Name of the Alert",
            example = "High CPU Usage"
    )
    private String name;

    @Schema(
            description = "Description of the Alert",
            example = "CPU usage is above 90%"
    )
    private String description;

    @Schema(
            description = "Type of the Alert",
            example = "CPU"
    )
    private String type;

    @Schema(
            description = "Status of the Alert",
            example = "Open"
    )
    private String status;

    @Schema(
            description = "Severity of the Alert",
            example = "HIGH"
    )
    public enum severity {
        LOW,
        MEDIUM,
        HIGH
    }

    @Schema(
            description = "Minimum threshold for the Alert",
            example = "3"
    )
    private Long minThreshold;

    @Schema(
            description = "Maximum threshold for the Alert",
            example = "5"
    )
    private Long maxThreshold;

    @Schema(
            description = "Supervisor of the Alert",
            example = "John Doe"
    )
    private String supervisor;

    @Schema(
            description = "Agent of the Alert",
            example = "Jane Doe"
    )
    private String agent;

    @Schema(
            description = "Created At timestamp of the Alert",
            example = "2021-12-31T23:59:59"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(
            description = "Updated At timestamp of the Alert",
            example = "2021-12-31T23:59:59"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    // Setter personalizado para el enum 'severity'
    public void setSeverity(severity severe) {
        severity = severe;
    }
}
