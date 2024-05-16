package com.springboot.connectmate.services;

import com.springboot.connectmate.models.MetricsInfo;
import com.springboot.connectmate.models.ThresholdBreaches;

public interface MetricsInfoService {

    MetricsInfo findById(Long id);

    void addThresholdBreaches(int id, ThresholdBreaches thresholdBreaches);

}