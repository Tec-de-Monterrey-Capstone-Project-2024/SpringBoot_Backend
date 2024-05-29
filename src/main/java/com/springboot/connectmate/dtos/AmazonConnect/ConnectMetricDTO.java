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
    @Schema(
            description = "Identifier (id) of the queue, in this case is a string of " +
                    "hexadecimal numbers separated by '-' (not a number).",
            example = "0b777196-086d-46b6-ac21-6b145e65baad"
    )
    private String queueId;

    @Schema(
            description = "The name of the metric (all characters in upper case)",
            examples = {"CONTACTS_QUEUED", "CONTACTS_HANDLED", "CONTACTS_ABANDONED", "CONTACTS_CONSULTED",
                        "CONTACTS_AGENT_HUNG_UP_FIRST", "CONTACTS_HANDLED_INCOMING", "CONTACTS_HANDLED_OUTBOUND",
                        "CONTACTS_HOLD_ABANDONS", "CONTACTS_TRANSFERRED_IN", "CONTACTS_TRANSFERRED_OUT",
                        "CONTACTS_TRANSFERRED_IN_FROM_QUEUE", "CONTACTS_TRANSFERRED_OUT_FROM_QUEUE", "CONTACTS_MISSED",
                        "CALLBACK_CONTACTS_HANDLED", "API_CONTACTS_HANDLED", "OCCUPANCY", "HANDLE_TIME",
                        "AFTER_CONTACT_WORK_TIME", "QUEUED_TIME", "ABANDON_TIME", "QUEUE_ANSWER_TIME", "HOLD_TIME",
                        "INTERACTION_TIME", "INTERACTION_AND_HOLD_TIME", "SERVICE_LEVEL"}
    )
    private HistoricalMetricName name;

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
