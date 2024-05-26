package com.springboot.connectmate.dtos.AmazonConnect;

import lombok.Data;

@Data
public class InsightDTO {
    private String insightName;
    private String insightSummary;
    private String insightDescription;
    private String insightActions;
    private String insightCategory;
    private String insightPerformance;
    private String insightRootCause;
    private String insightImpact;
    private String insightPrevention;
}
