package com.springboot.connectmate.dtos.ThresholdBreachInsight;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "KPIDataContextDTO",
        description = "DTO for the kpi prompt"
)
public class KPIDataContextDTO {

    @Schema(
            description = "Name for the metric (name of the code)",
            example = "Service Level"
    )
    private String metric;

    @Schema(
            description = "Description of the metric",
            example = "The percentage of contacts answered within a specified timeframe."
    )
    private String metric_description;

    @Schema(
            description = "Additional information for the metric",
            example = "Indicates the speed and efficiency of the call handling process."
    )
    private String metric_additional_info;

    @Schema(
            description = "Name of the connect item (instance, queue, agent) to which the metric belongs + id",
            example = "Agent 1"
    )
    private String belongs_to;

    @Schema(
            description = "Minimum threshold value for the metric",
            example = "70"
    )

    private Double minimum_thresholdValue;

    @Schema(
            description = "Maximum threshold value for the metric",
            example = "90"
    )
    private Double maximum_thresholdValue;

    @Schema(
            description = "Target value for the metric",
            example = "85"
    )
    private Double target_value;

    @Schema(
            description = "Current value for the metric",
            example = "65"
    )
    private Double current_value;

    @Schema(
            description = "Type of Metric (Agent, Queue, Instance)",
            example = "2021-08-25T10:15:30"
    )
    private String metric_type;

    @Schema(
            description = "Additional information for the metric ()",
            example = "The percentage of contacts answered within a specified timeframe."
    )
    private String metric_type_additional_info;

}