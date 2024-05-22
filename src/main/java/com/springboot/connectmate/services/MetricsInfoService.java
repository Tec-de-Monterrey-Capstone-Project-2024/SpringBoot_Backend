package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Metric.ThresholdBreachesRequestDTO;
import com.springboot.connectmate.models.MetricsInfo;

public interface MetricsInfoService {

    MetricsInfo findById(Long id);

    void addThresholdBreaches(ThresholdBreachesRequestDTO thresholdBreaches);

}