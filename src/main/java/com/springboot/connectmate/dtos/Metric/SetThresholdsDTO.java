package com.springboot.connectmate.dtos.Metric;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SetThresholdsDTO {
    private Long metricId;
    private BigDecimal minimumThreshold;
    private BigDecimal maximumThreshold;
}
