package com.springboot.connectmate.dtos.Alert;

import com.springboot.connectmate.dtos.Metric.MetricDescriptionDTO;
import com.springboot.connectmate.enums.ThresholdBreachPerformanceCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(
        name = "Alert",
        description = "DTO for Alert"
)
public class AlertDTO {

    @Schema(
            description = "Id of the Alert",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Metric Description associated with this alert. " +
                    "Contains details such as metric ID, code, and " +
                    "description related to the specific metric causing " +
                    "the alert."
    )
    private MetricDescriptionDTO metric;

    @Schema(
            description = "Value of the Alert",
            example = "70"
    )
    private double value;

    @Schema(
            description = "Breach Category of the Alert",
            example = "CRITICAL"
    )
    private ThresholdBreachPerformanceCategory breachCategory;

    @Schema(
            description = "Occurred At of the Alert",
            example = "2021-07-01T00:00:00"
    )
    private LocalDateTime occurredAt;

}