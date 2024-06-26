package com.springboot.connectmate.dtos.ThresholdBreachInsight;

import com.springboot.connectmate.enums.InsightSeverity;
import com.springboot.connectmate.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(
        name = "ThresholdBreachInsightDetailDTO",
        description = "DTO for Threshold Breach Insight Detail"
)
public class ThresholdBreachInsightGenericDTO {

    @Schema(
            description = "Unique identifier of the insight",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Name of the insight",
            example = "Average answer speed"
    )
    private String insightName;

    @Schema(
            description = "Summary of the insight",
            example = "The average answer speed the threshold."
    )
    private String insightSummary;

    @Schema(
            description = "Severity level of the insight",
            examples = {"LOW", "MEDIUM", "HIGH",  "CRITICAL", "UNKNOWN"}
    )
    private InsightSeverity insightSeverity;

    @Schema(
            description = "Status of the insight",
            examples = {"TO_DO", "IN_PROGRESS", "DONE"}
    )
    private Status status;
}
