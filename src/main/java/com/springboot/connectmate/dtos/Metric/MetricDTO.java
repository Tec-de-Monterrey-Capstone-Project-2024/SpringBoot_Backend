package com.springboot.connectmate.dtos.Metric;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "MetricDTO",
        description = "DTO for a ConnectMate Metric"
)
public class MetricDTO {
    private String code;
    private String name;
}
