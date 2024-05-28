package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Insight.ThresholdBreachInsightDTO;
import com.springboot.connectmate.dtos.Insight.UpdateStatusDTO;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.enums.Status;

import java.util.List;

public interface ThresholdBreachInsightService {
    List<ThresholdBreachInsightDTO> getInsightsByStatus(Status status);
    List<ThresholdBreachInsightDTO> getInsightsByConnectItemId(String connectItemId);
    List<ThresholdBreachInsightDTO> getAllInsights();
    List<ThresholdBreachInsightDTO> getInsightsByItemType(ConnectMetricType connectItemType);
    ThresholdBreachInsightDTO updateStatus(Long id, UpdateStatusDTO updateStatusDTO);
}
