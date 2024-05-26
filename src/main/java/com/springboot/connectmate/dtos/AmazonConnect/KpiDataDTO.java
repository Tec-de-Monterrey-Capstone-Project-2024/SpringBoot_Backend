package com.springboot.connectmate.dtos.AmazonConnect;

import lombok.Data;

@Data
public class KpiDataDTO {
    private Long id;
    private String metric;
    private String description;
    private boolean hasPositiveUpside;
    private boolean belongsToUser;
    private boolean belongsToQueue;
    private String unit;
    private String statistic;
    private Double minimumThresholdValue;
    private Double maximumThresholdValue;
    private Double targetValue;
    private Double currentValue;
    private boolean breachOccurred;
}
