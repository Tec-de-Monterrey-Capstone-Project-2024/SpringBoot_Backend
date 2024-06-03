package com.springboot.connectmate.dtos.ThresholdBreachInsight;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.connectmate.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        name = "InsightAlertDTO",
        description = "DTO for Alert´s Details "
)
public class InsightAlertDTO {
    @Schema(
            description = "Unique identifier of the insight",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Metric code associated with the insight",
            examples = {"SERVICE_LEVEL", "ABANDONMENT_RATE", "AVERAGE_SPEED_ANSWER",  "AVERAGE_HANDLE_TIME",
                    "OCCUPANCY", "FIRST_CONTACT_RESOLUTION", "AGENTS_AFTER_CONTACT_WORK", "SLOTS_ACTIVE",
                    "AVERAGE_RESOLUTION_TIME", "SCHEDULE_ADHERENCE", "VIRTUAL_FLOOR_RECONFIGURATION"}
    )
    private ConnectMetricCode metricCode;

    @Schema(
            description = "Category of the insight's performance",
            examples = {"CRITICAL", "UNSATISFACTORY", "BELOW_EXPECTATIONS",  "EXCEEDS_EXPECTATIONS",
                    "OUTSTANDING", "PIONEERING", "UNKNOWN"}
    )
    private InsightCategory insightCategory;

    @Schema(
            description = "Type of the Connect item",
            examples = {"AGENT", "QUEUE", "INSTANCE"}
    )
    private ConnectMetricType connectItemType;

    @Schema(
            description = "Timestamp when the insight occurred",
            type = "Timestamp",
            pattern = "EEE MMM dd HH:mm:ss zzz yyyy",
            example = "Fri May 03 17:29:27 CST 2024"
    )
    @JsonFormat(pattern = "EEE MMM dd HH:mm:ss zzz yyyy")
    private String occurredAt;

}
