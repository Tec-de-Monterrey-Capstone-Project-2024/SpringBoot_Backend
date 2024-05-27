package com.springboot.connectmate.dtos.AmazonConnect;

import com.amazonaws.services.connect.model.CurrentMetricName;
import com.amazonaws.services.connect.model.Unit;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "ConnectCurrentMetricDTO",
        description = "Data Transfer Object (DTO) for Real Time Metrics (RTM)"
)
public class ConnectCurrentMetricDTO {
    private String queueId;
    private CurrentMetricName name;
    private Double value;
    private Unit unit;
}
