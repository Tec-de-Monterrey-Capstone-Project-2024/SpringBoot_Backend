package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Insight.ThresholdBreachInsightDTO;
import com.springboot.connectmate.dtos.Insight.UpdateStatusFormDTO;
import com.springboot.connectmate.enums.ConnectMetricCode;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.models.ThresholdBreachInsight;

import java.util.List;

public interface ThresholdBreachInsightService {
    ThresholdBreachInsight getInsightByMetricCodeAndConnectItemId(ConnectMetricCode metricCode, String connectItemId);
    List<ThresholdBreachInsightDTO> getAllInsights();
    List<ThresholdBreachInsightDTO> getInsightsByStatus(Status status);
    List<ThresholdBreachInsightDTO> getInsightsByConnectItemId(String connectItemId);
    List<ThresholdBreachInsightDTO> getInsightsByItemType(ConnectMetricType connectItemType);
    String updateStatus(Long id, UpdateStatusFormDTO updateStatusFormDTO);
}
