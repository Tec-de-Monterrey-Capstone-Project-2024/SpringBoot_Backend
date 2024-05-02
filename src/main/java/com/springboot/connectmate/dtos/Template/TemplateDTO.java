package com.springboot.connectmate.dtos.Template;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "Template",
        description = "DTO for Template"

)
public class TemplateDTO {

    @Schema(
            description = "Name Template for Insight",
            example = "{{ metric_name }} of Queue {{ queue_name }}"
    )
    private String nameTemplate;

    @Schema(
            description = "Summary Template for Insight",
            example = "Summary: {{ metric_name }} breached threshold"
    )
    private String summaryTemplate;

    @Schema(
            description = "Situation Template for Insight",
            example = "The {{ metric_name }} of Queue {{ queue_name }} has exceeded the threshold"
    )
    private String situationTemplate;

    @Schema(
            description = "Actions Template for Insight",
            example = "Action: Investigate {{ queue_name }} to resolve {{ metric_name }} breach"
    )
    private String actionsTemplate;
}
