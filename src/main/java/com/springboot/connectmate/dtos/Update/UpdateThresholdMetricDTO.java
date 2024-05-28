package com.springboot.connectmate.dtos.Update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO for updating threshold values of a metric")
public class UpdateThresholdMetricDTO {

    @Schema(description = "The minimum threshold value", example = "0.5")
    private Double minimumThresholdValue;

    @Schema(description = "The maximum threshold value", example = "2.0")
    private Double maximumThresholdValue;

    @Schema(description = "The target value", example = "1.0")
    private Double targetValue;
}
