package com.springboot.connectmate.dtos.Insight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThresholdBreachInsightDTO {
    private Long id;
    private String metricCode;
    private String connectItemId;
    private String connectItemType;
    private Double value;
    private String occurredAt;
    private String status;
    private String insightName;
    private String insightSummary;
    private String insightDescription;
    private String insightActions;
    private String insightCategory;
    private String insightSeverity;
    private String insightRootCause;
    private String insightImpact;
    private String insightPrevention;


}


