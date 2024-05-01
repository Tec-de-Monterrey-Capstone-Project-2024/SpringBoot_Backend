package com.springboot.connectmate.dtos.Insight;

import com.springboot.connectmate.enums.InsightStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "Insight",
        description = "DTO for Insight"
)
public class InsightDTO {

    @Schema(
            description = "Id of the Insight",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Constructed description of the Insight",
            example = "This is a sample constructed description."
    )
    private String constructedDescription;

    @Schema(
            description = "Alert ID associated with the Insight",
            example = "1"
    )
    private Long thresholdBreachId;


    @Schema(
            description = "Status of the Insight",
            example = "TO_DO"
    )
    private InsightStatus status;

    @Schema(
            description = "Template summary of the Insight",
            example = "Consider reconfiguring the virtual floor."
    )
    private String summaryTemplate;

    @Schema(
            description = "Template situation of the Insight",
            example = "Reconfigure the virtual floor..."
    )
    private String situationTemplate;

    @Schema(
            description = "Template actions of the Insight",
            example = "1. Give incentives to high-performing agents."
    )
    private String actionsTemplate;
}

