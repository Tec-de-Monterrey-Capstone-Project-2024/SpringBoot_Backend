package com.springboot.connectmate.enums;

import com.amazonaws.services.connect.model.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConnectMetricCode {
    SERVICE_LEVEL(
            "Service Level",
            "The percentage of calls answered within 20 seconds",
            "Important for assessing the efficiency and responsiveness of the service team.",
            Unit.PERCENT,
            ConnectMetricApiType.MetricDataV2,
            80.0,
            "To ensure a high level of customer satisfaction by minimizing wait times."
    ),
    ABANDONMENT_RATE(
            "Abandonment Rate",
            "The percentage of calls abandoned by customers before being answered.",
            "Crucial for understanding customer patience and service efficiency.",
            Unit.PERCENT,
            ConnectMetricApiType.MetricDataV2,
            0.0,
            "To reduce the number of abandoned calls and improve customer experience."
    ),
    AVERAGE_SPEED_ANSWER(
            "Average Speed Answer",
            "The average time it takes to answer a call.",
            "Indicates the speed and efficiency of the call handling process.",
            Unit.SECONDS,
            ConnectMetricApiType.MetricDataV2,
            0.0,
            "To minimize the wait time for customers and improve service efficiency."
    ),
    AVERAGE_HANDLE_TIME(
            "Average Handle Time",
            "The average time taken to handle contacts.",
            "Crucial for evaluating the efficiency of agents.",
            Unit.SECONDS,
            ConnectMetricApiType.MetricDataV2,
            0.0,
            "To optimize and reduce the time spent per contact."
    ),
    OCCUPANCY(
            "Occupancy",
            "The percentage of time agents are occupied with calls compared to their total working time.",
            "Reflects the workload and productivity of agents.",
            Unit.PERCENT,
            ConnectMetricApiType.MetricDataV2,
            0.0,
            "To balance agent workload and ensure efficient use of time."
    ),
    FIRST_CONTACT_RESOLUTION(
            "First Contact Resolution",
            "The percentage of contacts resolved on the first interaction.",
            "Indicates the effectiveness of agents in resolving issues quickly.",
            Unit.PERCENT,
            ConnectMetricApiType.MetricDataV2,
            0.0,
            "To improve customer satisfaction by resolving issues promptly."
    ),
    AGENTS_AFTER_CONTACT_WORK(
            "Agents After Contact Work",
            "The time agents spend completing tasks after a call.",
            "Important for understanding the total time agents are engaged with a contact.",
            Unit.SECONDS,
            ConnectMetricApiType.MetricDataV2,
            0.0,
            "To manage and reduce the after-call workload for agents."
    ),
    SLOTS_ACTIVE(
            "Slots Active",
            "The number of active slots available for calls.",
            "Reflects the system's capacity to handle calls.",
            Unit.COUNT,
            ConnectMetricApiType.MetricDataV2,
            0.0,
            "To ensure the system can handle the call volume effectively."
    ),
    AVERAGE_RESOLUTION_TIME(
            "Average Resolution Time",
            "The average time taken to resolve customer issues.",
            "Indicates the efficiency of problem-solving processes.",
            Unit.SECONDS,
            ConnectMetricApiType.MetricDataV2,
            0.0,
            "To reduce the time taken to resolve issues and improve customer satisfaction."
    ),
    SCHEDULE_ADHERENCE(
            "Schedule Adherence",
            "The degree to which agents stick to their scheduled work times.",
            "Critical for workforce management and planning.",
            Unit.PERCENT,
            ConnectMetricApiType.MetricDataV2,
            0.0,
            "To ensure agents are available as planned and improve service coverage."
    ),
    VIRTUAL_FLOOR_RECONFIGURATION(
            "Virtual Floor Reconfiguration",
            "The process of adjusting virtual agent floor plans to meet demand.",
            "Helps in managing and optimizing the distribution of virtual agents.",
            Unit.COUNT,
            ConnectMetricApiType.MetricDataV2,
            0.0,
            "To maintain an efficient and adaptable virtual agent environment."
    );

    private final String name;
    private final String description;
    private final String additionalInfo;
    private final Unit units;
    private final ConnectMetricApiType apiType;
    private final Double defaultTargetValue;
    private final String rationale;
}
