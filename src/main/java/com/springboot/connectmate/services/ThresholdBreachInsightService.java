package com.springboot.connectmate.services;

import com.springboot.connectmate.models.ThresholdBreachInsight;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachInsightDetailDTO;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.InsightDTO;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.models.Metric;

import java.util.List;


public interface ThresholdBreachInsightService {

    // Internal Use Only
    ThresholdBreachInsight generateAndSaveInsight(ThresholdBreachInsightDetailDTO dto, InsightDTO insight);
    ThresholdBreachInsight getInsightByMetricCodeAndConnectItemId(Metric metric, String connectItemId);

    // Get all insights (filter also)
    List<ThresholdBreachInsightDetailDTO> getAllInsights();
    List<ThresholdBreachInsightDetailDTO> getInsightsByStatus(Status status);
    List<ThresholdBreachInsightDetailDTO> getInsightsByConnectItemId(String connectItemId);
    List<ThresholdBreachInsightDetailDTO> getInsightsByItemType(ConnectMetricType connectItemType);

    // Update the status of the insight
    String updateStatus(Long id, Status status);
}
