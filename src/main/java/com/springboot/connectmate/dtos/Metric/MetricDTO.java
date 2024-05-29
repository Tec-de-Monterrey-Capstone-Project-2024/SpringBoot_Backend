package com.springboot.connectmate.dtos.Metric;

import com.springboot.connectmate.enums.ConnectMetricCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "MetricDTO",
        description = "DTO for a ConnectMate Metric"
)
public class MetricDTO {

    @Schema(
            description = "The code of the metric",
            example = "SERVICE_LEVEL"
    )
    private ConnectMetricCode code;

    @Schema(
            description = "The minimum threshold value of the metric",
            example = "0.0"
    )
    private Double minimumThresholdValue;

    @Schema(
            description = "The maximum threshold value of the metric",
            example = "100.0"
    )
    private Double maximumThresholdValue;

    @Schema(
            description = "The target value of the metric",
            example = "80.0"
    )
    private Double targetValue;

}
