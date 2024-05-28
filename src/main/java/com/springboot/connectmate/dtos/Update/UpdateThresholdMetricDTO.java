package com.springboot.connectmate.dtos.Update;

import lombok.Data;

@Data
public class UpdateThresholdMetricDTO {
    private Double minimumThresholdValue;
    private Double maximumThresholdValue;
    private Double targetValue;
}
