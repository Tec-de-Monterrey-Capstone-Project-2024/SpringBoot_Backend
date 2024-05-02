package com.springboot.connectmate.dtos.Metric;

import com.springboot.connectmate.enums.MetricCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "MetricDescription",
        description = "DTO for Metric Description"
)
public class MetricDescriptionDTO {

    @Schema(
            description = "Id of the Metric",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Code of the Metric",
            example = "SL"
    )
    private MetricCategory code;

    @Schema(
            description = "Name of the Metric",
            example = "Service Level"
    )
    private String description;

    @Schema(
            description = "Queue Id of the Metric",
            example = "1"
    )
    private Long queueId;

    @Schema(
            description = "User Id of the Metric",
            example = "1"
    )
    private Long userId;
}
