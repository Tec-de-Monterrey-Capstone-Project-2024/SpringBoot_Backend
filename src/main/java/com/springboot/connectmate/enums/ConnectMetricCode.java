package com.springboot.connectmate.enums;

import com.amazonaws.services.connect.model.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum ConnectMetricCode {
    SERVICE_LEVEL(
            "Service Level",
            "The percentage of calls answered within 20 seconds.",
            "Important for assessing the efficiency and responsiveness of the service team.",
            80.0,
            100.0,
            0.0,
            Unit.PERCENT,
            ConnectMetricApiType.MetricDataV2,
            "To ensure a high level of customer satisfaction by minimizing wait times.",
            Arrays.asList(ConnectMetricType.INSTANCE, ConnectMetricType.QUEUE, ConnectMetricType.AGENT)
    ),
    ABANDONMENT_RATE(
            "Abandonment Rate",
            "The percentage of calls abandoned by customers before being answered.",
            "Crucial for understanding customer patience and service efficiency.",
            5.0,
            100.0,
            0.0,
            Unit.PERCENT,
            ConnectMetricApiType.MetricDataV2,
            "To reduce the number of abandoned calls and improve customer experience.",
            Arrays.asList(ConnectMetricType.QUEUE, ConnectMetricType.AGENT)
    ),
    AVERAGE_SPEED_ANSWER(
            "Average Speed Answer",
            "The average time it takes to answer a call.",
            "Indicates the speed and efficiency of the call handling process.",
            20.0,
            100.0,
            0.0,
            Unit.SECONDS,
            ConnectMetricApiType.MetricDataV2,
            "To minimize the wait time for customers and improve service efficiency.",
            Arrays.asList(ConnectMetricType.QUEUE)
    ),
    AVERAGE_HANDLE_TIME(
            "Average Handle Time",
            "The average time taken to handle contacts.",
            "Crucial for evaluating the efficiency of agents.",
            300.0,
            Double.POSITIVE_INFINITY,
            0.0,
            Unit.SECONDS,
            ConnectMetricApiType.MetricDataV2,
            "To optimize and reduce the time spent per contact.",
            Arrays.asList(ConnectMetricType.QUEUE, ConnectMetricType.AGENT)
    ),
    OCCUPANCY(
            "Occupancy",
            "The percentage of time agents are occupied with calls compared to their total working time.",
            "Reflects the workload and productivity of agents.",
            85.0,
            100.0,
            0.0,
            Unit.PERCENT,
            ConnectMetricApiType.MetricDataV2,
            "To balance agent workload and ensure efficient use of time.",
            Arrays.asList(ConnectMetricType.AGENT)
    ),
    FIRST_CONTACT_RESOLUTION(
            "First Contact Resolution",
            "The percentage of contacts resolved on the first interaction.",
            "Indicates the effectiveness of agents in resolving issues quickly.",
            72.5,
            100.0,
            0.0,
            Unit.PERCENT,
            ConnectMetricApiType.MetricDataV2,
            "To improve customer satisfaction by resolving issues promptly.",
            Arrays.asList(ConnectMetricType.QUEUE)
    ),
    AGENT_SCHEDULE_ADHERENCE(
            "Agent Schedule Adherence",
            "The percentage of time agents adhere to their scheduled working hours.",
            "Important for ensuring that agents are available and working as per their schedules.",
            90.0,
            100.0,
            0.0,
            Unit.PERCENT,
            ConnectMetricApiType.MetricDataV2,
            "To ensure agents are following their schedules, which improves overall operational efficiency.",
            Arrays.asList(ConnectMetricType.AGENT)
    ),
    AVERAGE_AFTER_CONTACT_WORK_TIME(
            "Average After Contact Work Time",
            "The average time taken by agents to complete work related to the contact after the call.",
            "Important for understanding the overall workload of agents and optimizing post-call activities.",
            60.0,
            Double.POSITIVE_INFINITY,
            0.0,
            Unit.SECONDS,
            ConnectMetricApiType.MetricDataV2,
            "To ensure that agents are completing necessary tasks efficiently.",
            Arrays.asList(ConnectMetricType.QUEUE, ConnectMetricType.AGENT)
    ),
    AVERAGE_QUEUE_ANSWER_TIME(
            "Average Queue Answer Time",
            "The average time a call spends in the queue before being answered.",
            "Important for assessing the efficiency of the queue management and the customer wait time.",
            30.0,
            300.0,
            0.0,
            Unit.SECONDS,
            ConnectMetricApiType.MetricDataV2,
            "To minimize the wait time for customers in the queue and improve their experience.",
            Arrays.asList(ConnectMetricType.QUEUE)
    );

    private final String name;
    private final String description;
    private final String additionalInfo;
    private final Double defaultTargetValue;
    private final Double upperBound;
    private final Double lowerBound;
    private final Unit units;
    private final ConnectMetricApiType apiType;
    private final String rationale;
    private final List<ConnectMetricType> metricTypes;
}
