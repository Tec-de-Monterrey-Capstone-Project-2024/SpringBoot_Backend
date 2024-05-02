package com.springboot.connectmate.dtos.Metric;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Schema(
        name = "Metrics stored in the database",
        description = "DTO for the stored metric"
)
public class ConnectMetricDTO {

    @Schema(
            description = "Id of the Metric",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Code of the Metric",
            example = "SL"
    )
    private String metric_info_code;

    @Schema(
            description = "Value of the metric",
            example = "100"
    )
    private BigDecimal value;

    @Schema(
            description = "User Id of the Metric",
            example = "1"
    )
    private Long agent_id;

    @Schema(
            description = "Queue Id of the Metric",
            example = "1"
    )
    private Long queue_id;
}
