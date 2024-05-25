package com.springboot.connectmate.enums;

import com.amazonaws.services.connect.model.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConnectMetricCode {
    SERVICE_LEVEL(
            "Service Level",
            "The percentage of calls answered within 20 seconds.",
            "Important for assessing the efficiency and responsiveness of the service team.",
            Unit.PERCENT,
            ConnectMetricApiType.MetricDataV2,
            80.0,
            "To ensure a high level of customer satisfaction by minimizing wait times."
    );

    private final String name;
    private final String description;
    private final String additionalInfo;
    private final Unit units;
    private final ConnectMetricApiType apiType;
    private final Double defaultTargetValue;
    private final String rationale;
}
