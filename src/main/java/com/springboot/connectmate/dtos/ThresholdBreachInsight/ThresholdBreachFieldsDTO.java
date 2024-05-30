package com.springboot.connectmate.dtos.ThresholdBreachInsight;

import com.springboot.connectmate.enums.ConnectMetricType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "ThresholdBreachFieldsDTO",
        description = "DTO for the Threshold Breach Fields"
)
public class ThresholdBreachFieldsDTO {

    @Schema(
            description = "ID for a connect item (instance, queue, agent)",
            example = "agent123"
    )
    private String connectItemId;

    @Schema(
            description = "Type of connect item (instance, queue, agent)",
            example = "AGENT"
    )
    private ConnectMetricType connectItemType;

    @Schema(
            description = "Value of the metric",
            example = "80"
    )
    private Double value;

    @Schema(
            description = "Time when the threshold breach occurred",
            example = "2021-08-25T10:15:30"
    )
    private LocalDateTime occurredAt;
}
