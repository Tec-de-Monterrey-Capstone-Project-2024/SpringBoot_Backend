package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Metric.ThresholdBreachInsightDTO;
import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.enums.ConnectItemType;

import java.util.List;

public interface ThresholdBreachInsightService {
    List<ThresholdBreachInsightDTO> getInsightsByStatus(Status status);
    List<ThresholdBreachInsightDTO> getInsightsByConnectItemId(String connectItemId);
    List<ThresholdBreachInsightDTO> getAllInsights();
    List<ThresholdBreachInsightDTO> getInsightsByItemType(ConnectItemType connectItemType);
}
