package com.springboot.connectmate.services;

import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.models.ThresholdBreachInsight;

public interface ThresholdBreachInsightService {
    ThresholdBreachInsight updateStatus(Long id, Status status);
}
