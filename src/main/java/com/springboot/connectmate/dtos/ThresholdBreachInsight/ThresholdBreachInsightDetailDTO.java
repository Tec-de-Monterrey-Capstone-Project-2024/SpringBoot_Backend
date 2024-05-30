package com.springboot.connectmate.dtos.ThresholdBreachInsight;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.connectmate.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "ThresholdBreachInsightDetailDTO",
        description = "DTO for Threshold Breach Insight Detail"
)
public class ThresholdBreachInsightDetailDTO {

    @Schema(
            description = "Unique identifier of the insight",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Metric code associated with the insight",
            examples = {"SERVICE_LEVEL", "ABANDONMENT_RATE", "AVERAGE_SPEED_ANSWER",  "AVERAGE_HANDLE_TIME",
                    "OCCUPANCY", "FIRST_CONTACT_RESOLUTION", "AGENTS_AFTER_CONTACT_WORK", "SLOTS_ACTIVE",
                    "AVERAGE_RESOLUTION_TIME", "SCHEDULE_ADHERENCE", "VIRTUAL_FLOOR_RECONFIGURATION"}
    )
    private ConnectMetricCode metricCode;

    @Schema(
            description = "Identifier of the Connect item",
            example = "1"
    )
    private String connectItemId;

    @Schema(
            description = "Type of the Connect item",
            examples = {"AGENT", "QUEUE", "INSTANCE"}
    )
    private ConnectMetricType connectItemType;

    @Schema(
            description = "Value of the metric",
            example = "95.5"
    )
    private Double value;

    @Schema(
            description = "Timestamp when the insight occurred",
            type = "Timestamp",
            pattern = "EEE MMM dd HH:mm:ss zzz yyyy",
            example = "Fri May 03 17:29:27 CST 2024"
    )
    @JsonFormat(pattern = "EEE MMM dd HH:mm:ss zzz yyyy")
    private String occurredAt;

    @Schema(
            description = "Status of the insight",
            examples = {"TO_DO", "IN_PROGRESS", "DONE"}
    )
    private Status status;

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
            description = "Description of the insight",
            example = "Detailed description of the insight"
    )
    private String insightDescription;

    @Schema(
            description = "Recommended actions for the insight",
            example = "Increase agent availability"
    )
    private String insightActions;

    @Schema(
            description = "Category of the insight's performance",
            examples = {"CRITICAL", "UNSATISFACTORY", "BELOW_EXPECTATIONS",  "EXCEEDS_EXPECTATIONS",
                    "OUTSTANDING", "PIONEERING", "UNKNOWN"}
    )
    private InsightPerformance insightCategory;

    @Schema(
            description = "Severity level of the insight",
            examples = {"LOW", "MEDIUM", "HIGH",  "CRITICAL", "UNKNOWN"}
    )
    private InsightSeverity insightSeverity;

    @Schema(
            description = "Root cause of the insight",
            example = "Improve Average Answer speed"
    )
    private String insightRootCause;

    @Schema(
            description = "Impact of the insight",
            example = "Longer wait times for customers"
    )
    private String insightImpact;

    @Schema(
            description = "Prevention measures for the insight",
            example = "Take actions to improve average answer speed"
    )
    private String insightPrevention;

}
