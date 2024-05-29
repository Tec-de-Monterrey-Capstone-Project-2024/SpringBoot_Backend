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
    @Schema(
            description = "Identifier (id) of the queue, in this case is a string of " +
                          "hexadecimal numbers separated by '-' (not a number).",
            example = "0b777196-086d-46b6-ac21-6b145e65baad"
    )
    private String queueId;

    @Schema(
            description = "The name of the metric (all characters in upper case)",
            examples = {"AGENTS_ONLINE", "AGENTS_AVAILABLE", "AGENTS_ON_CALL", "AGENTS_NON_PRODUCTIVE",
                        "AGENTS_AFTER_CONTACT_WORK", "AGENTS_ERROR", "AGENTS_STAFFED", "CONTACTS_IN_QUEUE",
                        "OLDEST_CONTACT_AGE", "CONTACTS_SCHEDULED", "AGENTS_ON_CONTACT", "SLOTS_ACTIVE",
                        "SLOTS_AVAILABLE"}
    )
    private CurrentMetricName name;

    @Schema(
            description = "The value of the metric",
            example = "77.3"
    )
    private Double value;

    @Schema(
            description = "The unit of the value given for the metric",
            examples = {"SECONDS", "COUNT", "PERCENT"}
    )
    private Unit unit;
}
