package com.springboot.connectmate.dtos.Metric;

import com.springboot.connectmate.enums.Code;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods based on the fields
@Schema(
        name = "Metric",
        description = "DTO for description"
)
public class MetricDTO {
    @Schema(
            description = "Category of the metric.",
            example = "SL"
    )
    private Code code;

    @Schema(
            description = "Name of the category of the metric.",
            example = "Service Level"
    )
    private String name;

    @Schema(
            description = "The value of the metric",
            example = "8.7"
    )
    private BigDecimal value;
}
