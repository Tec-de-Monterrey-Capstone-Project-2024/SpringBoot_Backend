package com.springboot.connectmate.dtos.AmazonConnect;

import com.springboot.connectmate.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Schema(description = "DTO for Threshold Breach Insight data")
public class ThresholdBreachInsightDTO {

    @Schema(description = "Unique identifier for the insight",
            example = "1")
    private Long id;

    @Schema(description = "ID of the connect item",
            example = "12345")
    private String connectItemId;

    @Schema(description = "Type of connect item",
            example = "AGENT")
    private ConnectMetricType connectItemType;

    @Schema(description = "Actions recommended for the insight",
            example = "Increase staffing levels")
    private String insightActions;

    @Schema(description = "Category of the insight's performance",
            example = "CRITICAL")
    private InsightPerformance insightCategory;

    @Schema(description = "Detailed description of the insight",
            example = "The contact center is not meeting its target service level of 85%. Immediate action is required to address this issue.")
    private String insightDescription;

    @Schema(description = "Impact of the threshold breach",
            example = "Reduced customer satisfaction and increased wait times")
    private String insightImpact;

    @Schema(description = "Name of the insight",
            example = "Service Level Breach")
    private String insightName;

    @Schema(description = "Prevention measures for future breaches",
            example = "Implement better workforce management and training programs")
    private String insightPrevention;

    @Schema(description = "Root cause of the threshold breach",
            example = "Insufficient staffing during peak hours")
    private String insightRootCause;

    @Schema(description = "Severity of the insight", example =
            "HIGH")
    private InsightSeverity insightSeverity;

    @Schema(description = "Summary of the insight", example =
            "The service level of the contact center has breached the threshold of 85%.")
    private String insightSummary;

    @Schema(description = "Timestamp when the threshold breach occurred",
            example = "2024-06-01T12:00:00")
    private LocalDateTime occurredAt;

    @Schema(description = "Status of the insight",
            example = "TO_DO")
    private Status status;

    @Schema(description = "Value of the metric",
            example = "65.0")
    private Double value;

    @Schema(description = "Code of the metric",
            example = "SERVICE_LEVEL")
    private ConnectMetricCode metricCode;
}
