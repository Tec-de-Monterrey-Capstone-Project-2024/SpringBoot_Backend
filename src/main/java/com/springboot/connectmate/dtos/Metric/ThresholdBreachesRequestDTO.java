package com.springboot.connectmate.dtos.Metric;

import com.springboot.connectmate.enums.Performance;
import com.springboot.connectmate.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(
        name = "ThresholdBreachesRequestDTO"
)
public class ThresholdBreachesRequestDTO {
    private Long metricsInfoId;

    @Schema(
            nullable = true
    )
    private String agentConnectId;
    private String queueId;
    private BigDecimal value;
    private Performance performance;
    private LocalDateTime occurredAt;
    private Status status;
}
