package com.springboot.connectmate.dtos.Update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "UpdateThresholdMetricDTO",
        description = "DTO for updating the threshold values of a metric"
)
public class UpdateThresholdMetricDTO {
    @Schema(description = "Minimum threshold value", example = "5.0")
    private Double minimumThresholdValue;

    @Schema(description = "Maximum threshold value", example = "95.0")
    private Double maximumThresholdValue;

    @Schema(description = "Target value", example = "80.0")
    private Double targetValue;
}
