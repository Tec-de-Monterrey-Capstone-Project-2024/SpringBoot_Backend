package com.springboot.connectmate.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConnectMetricType {

    INSTANCE(
            "Instance Metrics",
            "Metrics related to Amazon Connect instances.",
            "These metrics help monitor the overall performance and health of the Connect instances."
    ),
    QUEUE(
            "Queue Metrics",
            "Metrics related to call queues in Amazon Connect.",
            "These metrics provide insights into the efficiency and performance of call queues."
    ),
    AGENT(
            "Agent Metrics",
            "Metrics related to agents in Amazon Connect.",
            "These metrics evaluate agent performance and availability,."
    );

    private final String name;
    private final String description;
    private final String additionalInfo;
}
