package com.springboot.connectmate.enums;

import com.amazonaws.services.connect.model.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConnectMetricType {
    INSTANCE(
            "Instance Metrics",
            "Metrics related to Amazon Connect instances.",
            "These metrics help monitor the overall performance and health of Connect instances, including status, creation time, and other instance-specific information.",
            Unit.COUNT,
            ConnectMetricApiType.MetricDataV2,
            0.0,
            "To ensure the optimal performance and availability of Connect instances."
    ),
    QUEUE(
            "Queue Metrics",
            "Metrics related to call queues in Amazon Connect.",
            "These metrics provide insights into the efficiency and performance of call queues, including queue length, wait times, and handling times.",
            Unit.COUNT,
            ConnectMetricApiType.MetricDataV2,
            0.0,
            "To optimize call handling and reduce customer wait times."
    ),
    AGENT(
            "Agent Metrics",
            "Metrics related to agents in Amazon Connect.",
            "These metrics evaluate agent performance and availability, including status types, number of agents available, and handling times.",
            Unit.COUNT,
            ConnectMetricApiType.MetricDataV2,
            0.0,
            "To ensure sufficient agent availability and measure performance."
    );

    private final String name;
    private final String description;
    private final String additionalInfo;
    private final Unit units;
    private final ConnectMetricApiType apiType;
    private final Double defaultTargetValue;
    private final String rationale;
}
