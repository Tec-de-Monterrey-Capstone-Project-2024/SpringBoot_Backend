package com.springboot.connectmate.dtos.MockMetric;

import com.amazonaws.services.connect.model.Unit;
import com.springboot.connectmate.enums.ConnectMetricCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "MockMetricDTO",
        description = "DTO for a Mock Metric"
)
public class MockMetricDTO {

    @Schema(
            description = "The code of the metric",
            example = "SERVICE_LEVEL"
    )
    private ConnectMetricCode code;

    @Schema(
            description = "The name of the metric",
            example = "Service Level"
    )
    private String name;

    @Schema(
            description = "The unit of the metric",
            example = "PERCENT"
    )
    private Unit unit;

    @Schema(
            description = "The value of the metric",
            example = "80.0"
    )
    private double value;

}
