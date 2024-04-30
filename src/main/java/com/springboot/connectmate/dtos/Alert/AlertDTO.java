package com.springboot.connectmate.dtos.Alert;

import com.springboot.connectmate.dtos.Metric.MetricDescriptionDTO;
import com.springboot.connectmate.enums.ThresholdBreachPerformanceCategory;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(name = "AlertDTO", description = "Data Transfer Object for Alert")
public class AlertDTO {

    @Schema(description = "Unique identifier of the alert", example = "1")
    private Long id;

    @Schema(description = "Description of the metric causing the alert",
            example = "Metric causing the alert")
    private MetricDescriptionDTO metric;

    @Schema(description = "Value of the alert", example = "70")
    private double value;

    @Schema(description = "Category of the breach", example = "CRITICAL")
    private ThresholdBreachPerformanceCategory breachCategory;

    @Schema(description = "Time when the alert occurred", example = "2021-07-01T00:00:00")
    private LocalDateTime occurredAt;
}
