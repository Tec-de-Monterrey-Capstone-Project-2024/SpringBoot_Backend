package com.springboot.connectmate.dtos.ThresholdBreachInsight;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "InsightDTO",
        description = "DTO for the Insight generation"
)
public class InsightDTO {
    @Schema(
            description = "Name of the insight",
            example = "First Call Resolution Rate"
    )
    private String insightName;

    @Schema(
            description = "Summary of the insight",
            example = "The First Call Resolution (FCR) metric indicates the percentage of customer issues resolved during the initial call to the contact center."
    )
    private String insightSummary;

    @Schema(
            description = "Detailed description of the insight",
            example = "The First Call Resolution (FCR) metric measures the percentage of customer issues that are resolved during the initial call to the contact center. A higher FCR indicates that the contact center is effectively addressing customer concerns and providing timely resolutions."
    )
    private String insightDescription;

    @Schema(
            description = "Recommended actions based on the insight",
            example = "Increase training for agents to improve their ability to resolve issues on the first call."
    )
    private String insightActions;

    @Schema(
            description = "Category of the insight, indicating severity or impact",
            example = "MEDIUM"
    )
    private String insightCategory;

    @Schema(
            description = "Performance level associated with the insight",
            example = "BELOW_EXPECTATIONS"
    )
    private String insightPerformance;

    @Schema(
            description = "Root cause of the issue highlighted by the insight",
            example = "Inadequate training for agents leading to longer resolution times."
    )
    private String insightRootCause;

    @Schema(
            description = "Impact of the issue on the overall performance",
            example = "Reduced customer satisfaction and increased operational costs."
    )
    private String insightImpact;

    @Schema(
            description = "Recommendations for preventing similar issues in the future",
            example = "Implement regular training sessions and quality monitoring to ensure agents are equipped to handle customer issues effectively."
    )
    private String insightPrevention;

}