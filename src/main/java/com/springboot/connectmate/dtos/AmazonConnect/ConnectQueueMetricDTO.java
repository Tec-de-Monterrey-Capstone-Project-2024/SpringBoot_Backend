package com.springboot.connectmate.dtos.AmazonConnect;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "ConnectQueueMetricDTO",
        description = "Data Transfer Object (DTO) for queue metrics into a specified Amazon Connect instance"
)
public class ConnectQueueMetricDTO {
    @Schema(
            description = "Abandonment rate of the queue",
            example = "90"
    )
    private double abandonmentRate;
    @Schema(
            description = "Average Handle Time of the queue.",
            example = "20"
    )
    private double avgHandleTime;

    @Schema(
            description = "Average After Contact Work Time of the queue.",
            example = "135"
    )
    private double avgAfterContactWorkTime;

    @Schema(
            description = "Average Resolution Time of the queue.",
            example = "120"
    )
    private double avgResolutionTime;

    @Schema(
            description = "Average Queue Answer Time of the queue.",
            example = "10"
    )
    private double avgQueueAnswerTime;

    @Schema(
            description = "Service Level of the queue.",
            example = "90"
    )
    private double serviceLevel;
}
