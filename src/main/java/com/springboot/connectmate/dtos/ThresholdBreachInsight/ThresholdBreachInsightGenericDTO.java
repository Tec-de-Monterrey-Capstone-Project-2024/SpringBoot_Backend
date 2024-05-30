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


}
