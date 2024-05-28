package com.springboot.connectmate.dtos.AmazonConnect;

import com.springboot.connectmate.enums.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ThresholdBreachInsightDTO {
    private Long id;
    private String connectItemId;
    private ConnectMetricType connectItemType;
    private String insightActions;
    private InsightPerformance insightCategory;
    private String insightDescription;
    private String insightImpact;
    private String insightName;
    private String insightPrevention;
    private String insightRootCause;
    private InsightSeverity insightSeverity;
    private String insightSummary;
    private LocalDateTime occurredAt;
    private Status status;
    private Double value;
    private ConnectMetricCode metricCode;
}
