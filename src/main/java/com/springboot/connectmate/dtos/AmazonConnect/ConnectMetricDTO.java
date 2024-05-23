package com.springboot.connectmate.dtos.AmazonConnect;

import com.amazonaws.services.connect.model.HistoricalMetricName;
import com.amazonaws.services.connect.model.Unit;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "ConnectMetricDTO",
        description = "Data Transfer Object (DTO) for obtain average of historical metrics"
)
public class ConnectMetricDTO {
    private String queueId;
    private HistoricalMetricName name;
    private Double value;
    private Unit unit;
}
