package com.springboot.connectmate.dtos.Metric;

import com.springboot.connectmate.enums.MetricCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(
    name = "MetricThresholdsDTO",
    description = "DTO for the response after updating or removing thresholds from metrics"
)
public class MetricThresholdsDTO {
    @Schema(
        description = "Unique identifier of the metric.",
        example = "123"
    )
    private Long metricId;

    @Schema(
        description = "Name of the metric.",
        example = "Average Response Time"
    )
    private String name;

    @Schema(
        description = "Minimum threshold value of the metric, can be null if removed.",
        example = "10.00",
        nullable = true
    )
    private BigDecimal minimumThreshold;

    @Schema(
        description = "Maximum threshold value of the metric, can be null if removed.",
        example = "50.00",
        nullable = true
    )
    private BigDecimal maximumThreshold;
}
