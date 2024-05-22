package com.springboot.connectmate.dtos.Alert;

import com.springboot.connectmate.enums.Performance;
import com.springboot.connectmate.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Alert",
        description = "DTO for Threshold Breaches AKA alerts."
)
public class ThresholdBreachDTO {

    @Schema(
            description = "Id of the alert",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Id of the metric related to the breach.",
            example = "1"
    )
    private Long metricsInfoId;

    @Schema(
            description = "Connect Id of the agent related to this breach",
            example = "b618f4f4-d955-4006-be08-c8a2e8f427bc"
    )
    private String agentId;

    @Schema(
            description = "Id of the queue related to the breach.",
            example = "4896ae34-a93e-41bc-8231-bf189e7628b2"
    )
    private String queueId;

    @Schema(
            description = "Value that broke the thresholds for the metric.",
            example = "100"
    )
    private BigDecimal value;

    @Schema(
            description = "Performance of the metric that broke the threshold.",
            example = "PIONEERING"
    )
    private Performance performance;

    @Schema(
            description = "Timestamp when the breach occured.",
            example = "1986-04-08 12:30"
    )
    private LocalDateTime occurredAt;

    @Schema(
            description = "Status of the alert/insight.",
            example = "TO_DO"
    )
    private Status status;
}
