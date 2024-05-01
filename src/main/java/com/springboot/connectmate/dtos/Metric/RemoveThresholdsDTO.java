package com.springboot.connectmate.dtos.Metric;

import com.springboot.connectmate.enums.MetricCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data 
@Schema(
        name = "RemoveThresholdsDTO",
        description = "DTO for removing thresholds from metrics"
)
public class RemoveThresholdsDTO {
    @Schema(
            description = "ID of the user to filter metrics.",
            example = "1"
    )
    private Long userId;

    @Schema(
            description = "ID of the queue to filter metrics.",
            example = "1"
    )
    private Long queueId;

    @Schema(
            description = "Category code of the metric to filter metrics.",
            example = "SL"
    )
    private MetricCategory code;
}
