package com.springboot.connectmate.dtos.Insight;

import com.springboot.connectmate.enums.ConnectMetricCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThresholdBreachInsightDTO {

    @Schema(description = "Unique identifier of the insight", example = "1")
    private Long id;

    @Schema(description = "Metric code associated with the insight", example = "INSTANCE")
    private ConnectMetricCode metricCode;

    @Schema(description = "Identifier of the Connect item", example = "1")
    private String connectItemId;

    @Schema(description = "Type of the Connect item", example = "AGENT")
    private String connectItemType;

    @Schema(description = "Value of the metric", example = "95.5")
    private Double value;

    @Schema(description = "Timestamp when the insight occurred", example = "2023-05-26T15:30:00Z")
    private String occurredAt;

    @Schema(description = "Status of the insight", example = "TO_DO")
    private String status;

    @Schema(description = "Name of the insight", example = "Average answer speed")
    private String insightName;

    @Schema(description = "Summary of the insight", example = "The average answer speed the threshold.")
    private String insightSummary;

    @Schema(description = "Description of the insight", example = "Detailed description of the insight")
    private String insightDescription;

    @Schema(description = "Recommended actions for the insight", example = "Increase agent availability")
    private String insightActions;

    @Schema(description = "Category of the insight's performance", example = "CRITICAL")
    private String insightCategory;

    @Schema(description = "Severity level of the insight", example = "HIGH")
    private String insightSeverity;

    @Schema(description = "Root cause of the insight", example = "Improve Average Answer speed")
    private String insightRootCause;

    @Schema(description = "Impact of the insight", example = "Longer wait times for customers")
    private String insightImpact;

    @Schema(description = "Prevention measures for the insight", example = "Take actions to improve average answer speed")
    private String insightPrevention;
}


