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
            Unit.PERCENT,
            ConnectMetricApiType.MetricDataV2,
            80.0,
            100.0,
            0.0,
            "To ensure a high level of customer satisfaction by minimizing wait times.",
            Arrays.asList(ConnectMetricType.QUEUE)
    ),
    ABANDONMENT_RATE(
            "Abandonment Rate",
            "The percentage of calls abandoned by customers before being answered.",
            "Crucial for understanding customer patience and service efficiency.",
            Unit.PERCENT,
            ConnectMetricApiType.MetricDataV2,
            5.0,
            100.0,
            0.0,
            "To reduce the number of abandoned calls and improve customer experience.",
            Arrays.asList(ConnectMetricType.QUEUE, ConnectMetricType.AGENT)
    ),
    AVERAGE_SPEED_ANSWER(
            "Average Speed Answer",
            "The average time it takes to answer a call.",
            "Indicates the speed and efficiency of the call handling process.",
            Unit.SECONDS,
            ConnectMetricApiType.MetricDataV2,
            20.0,
            100.0,
            0.0,
            "To minimize the wait time for customers and improve service efficiency.",
            Arrays.asList(ConnectMetricType.QUEUE)
    ),
    AVERAGE_HANDLE_TIME(
            "Average Handle Time",
            "The average time taken to handle contacts.",
            "Crucial for evaluating the efficiency of agents.",
            Unit.SECONDS,
            ConnectMetricApiType.MetricDataV2,
            300.0,
            Double.POSITIVE_INFINITY,
            0.0,
            "To optimize and reduce the time spent per contact.",
            Arrays.asList(ConnectMetricType.QUEUE, ConnectMetricType.AGENT)
    ),
    OCCUPANCY(
            "Occupancy",
            "The percentage of time agents are occupied with calls compared to their total working time.",
            "Reflects the workload and productivity of agents.",
            Unit.PERCENT,
            ConnectMetricApiType.MetricDataV2,
            85.0,
            100.0,
            0.0,
            "To balance agent workload and ensure efficient use of time.",
            Arrays.asList(ConnectMetricType.AGENT)
    ),
    FIRST_CONTACT_RESOLUTION(
            "First Contact Resolution",
            "The percentage of contacts resolved on the first interaction.",
            "Indicates the effectiveness of agents in resolving issues quickly.",
            Unit.PERCENT,
            ConnectMetricApiType.MetricDataV2,
            72.5,
            100.0,
            0.0,
            "To improve customer satisfaction by resolving issues promptly.",
            Arrays.asList(ConnectMetricType.QUEUE)
    );

    private final String name;
    private final String description;
    private final String additionalInfo;
    private final Unit units;
    private final ConnectMetricApiType apiType;
    private final Double defaultTargetValue;
    private final Double upperBound;
    private final Double lowerBound;
    private final String rationale;
    private final List<ConnectMetricType> metricTypes;
}


/*package com.springboot.connectmate.enums;

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
            Unit.PERCENT,
            ConnectMetricApiType.MetricDataV2,
            80.0,
            100.0,
            0.0,
            "To ensure a high level of customer satisfaction by minimizing wait times.",
            Arrays.asList(ConnectMetricType.QUEUE),

            ABANDONMENT_RATE(
                    "Abandonment Rate",
                    "The percentage of calls abandoned by customers before being answered.",
                    "Crucial for understanding customer patience and service efficiency.",
                    Unit.PERCENT,
                    ConnectMetricApiType.MetricDataV2,
                    5.0, // Typical target in the industry is below 5%
                    "To reduce the number of abandoned calls and improve customer experience."
            ),
            AVERAGE_SPEED_ANSWER(
                    "Average Speed Answer",
                    "The average time it takes to answer a call.",
                    "Indicates the speed and efficiency of the call handling process.",
                    Unit.SECONDS,
                    ConnectMetricApiType.MetricDataV2,
                    20.0, // Industry standard to answer within 20 seconds
                    "To minimize the wait time for customers and improve service efficiency."
            ),
            AVERAGE_HANDLE_TIME(
                    "Average Handle Time",
                    "The average time taken to handle contacts.",
                    "Crucial for evaluating the efficiency of agents.",
                    Unit.SECONDS,
                    ConnectMetricApiType.MetricDataV2,
                    300.0, // Typical industry standard is 5 minutes (300 seconds)
                    "To optimize and reduce the time spent per contact."
            ),
            OCCUPANCY(
                    "Occupancy",
                    "The percentage of time agents are occupied with calls compared to their total working time.",
                    "Reflects the workload and productivity of agents.",
                    Unit.PERCENT,
                    ConnectMetricApiType.MetricDataV2,
                    85.0, // Industry standard target is around 85%
                    "To balance agent workload and ensure efficient use of time."
            ),
            FIRST_CONTACT_RESOLUTION(
                    "First Contact Resolution",
                    "The percentage of contacts resolved on the first interaction.",
                    "Indicates the effectiveness of agents in resolving issues quickly.",
                    Unit.PERCENT,
                    ConnectMetricApiType.MetricDataV2,
                    72.5, // Typical target is 70-75%
                    "To improve customer satisfaction by resolving issues promptly.");

    );

    private final String name;
    private final String description;
    private final String additionalInfo;
    private final Unit units;
    private final ConnectMetricApiType apiType;
    private final Double defaultTargetValue;
    private final Double upperBound;
    private final Double lowerBound;
    private final String rationale;
    private final List<ConnectMetricType> metricTypes;
    /*
    ABANDONMENT_RATE(
            "Abandonment Rate",
            "The percentage of calls abandoned by customers before being answered.",
            "Crucial for understanding customer patience and service efficiency.",
            Unit.PERCENT,
            ConnectMetricApiType.MetricDataV2,
            5.0, // Typical target in the industry is below 5%
            "To reduce the number of abandoned calls and improve customer experience."
    ),
    AVERAGE_SPEED_ANSWER(
            "Average Speed Answer",
            "The average time it takes to answer a call.",
            "Indicates the speed and efficiency of the call handling process.",
            Unit.SECONDS,
            ConnectMetricApiType.MetricDataV2,
            20.0, // Industry standard to answer within 20 seconds
            "To minimize the wait time for customers and improve service efficiency."
    ),
    AVERAGE_HANDLE_TIME(
            "Average Handle Time",
            "The average time taken to handle contacts.",
            "Crucial for evaluating the efficiency of agents.",
            Unit.SECONDS,
            ConnectMetricApiType.MetricDataV2,
            300.0, // Typical industry standard is 5 minutes (300 seconds)
            "To optimize and reduce the time spent per contact."
    ),
    OCCUPANCY(
            "Occupancy",
            "The percentage of time agents are occupied with calls compared to their total working time.",
            "Reflects the workload and productivity of agents.",
            Unit.PERCENT,
            ConnectMetricApiType.MetricDataV2,
            85.0, // Industry standard target is around 85%
            "To balance agent workload and ensure efficient use of time."
    ),
    FIRST_CONTACT_RESOLUTION(
            "First Contact Resolution",
            "The percentage of contacts resolved on the first interaction.",
            "Indicates the effectiveness of agents in resolving issues quickly.",
            Unit.PERCENT,
            ConnectMetricApiType.MetricDataV2,
            72.5, // Typical target is 70-75%
            "To improve customer satisfaction by resolving issues promptly."
    );


}
*/