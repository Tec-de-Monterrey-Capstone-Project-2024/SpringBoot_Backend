package com.springboot.connectmate.dtos.AmazonConnect;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "KpiDataDTO",
        description = "DTO for the kpi prompt"
)
public class KpiDataDTO {
    @Schema(
            description = "ID for the metric",
            example = "1"
    )
    private Long id;
    @Schema(
            description = "Name for the metric",
            example = "SERVICE_LEVEL"
    )
    private String metric;
    @Schema(
            description = "Description of the metric",
            example = "The percentage of contacts answered within a specified timeframe."
    )
    private String description;
    @Schema(
            description = "Indicates if the metric has a positive upside",
            example = "false"
    )
    private boolean hasPositiveUpside;
    @Schema(
            description = "Indicates if the metric belongs to the user",
            example = "true"
    )
    private boolean belongsToUser;
    @Schema(
            description = "Indicates if the metric belongs to the queue",
            example = "true"
    )
    private boolean belongsToQueue;
    @Schema(
            description = "Unit of the metric",
            example = "PERCENT"
    )
    private String unit;
    @Schema(
            description = "Statistic type of the metric",
            example = "AVG"
    )
    private String statistic;
    @Schema(
            description = "Minimum threshold value for the metric",
            example = "70"
    )
    private Double minimumThresholdValue;
    @Schema(
            description = "Maximum threshold value for the metric",
        example = "90"
    )
    private Double maximumThresholdValue;
    @Schema(
            description = "Target value for the metric",
            example = "85"
    )
    private Double targetValue;
    @Schema(
            description = "Current value for the metric",
            example = "65"
    )
    private Double currentValue;
    @Schema(
            description = "Indicates if a breach occurred for the metric",
            example = "true"
    )
    private boolean breachOccurred;
}
