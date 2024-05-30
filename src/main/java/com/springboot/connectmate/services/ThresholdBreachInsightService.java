package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachInsightGenericDTO;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachInsightDetailDTO;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.InsightDTO;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.models.Metric;

import java.util.List;
import java.util.Map;


public interface ThresholdBreachInsightService {

    // Internal Use Only
    ThresholdBreachInsight generateAndSaveInsight(ThresholdBreachInsightDetailDTO dto, InsightDTO insight);
    ThresholdBreachInsight getInsightByMetricCodeAndConnectItemId(Metric metric, String connectItemId);

    // Get Insights (filter also)
    List<ThresholdBreachInsightGenericDTO> getAllInsights();
    ThresholdBreachInsightDetailDTO getInsightById(Long id);
    List<ThresholdBreachInsightGenericDTO> getInsightsByStatus(Status status);
    List<ThresholdBreachInsightGenericDTO> getInsightsByConnectItemId(String connectItemId);
    List<ThresholdBreachInsightGenericDTO> getInsightsByItemType(ConnectMetricType connectItemType);

    ThresholdBreachInsight saveInsight(ThresholdBreachInsightDetailDTO insight);
    ThresholdBreachInsight saveInsight(ThresholdBreachInsight insight);

    // Get All Insights by Status
    Map<Status, List<ThresholdBreachInsightGenericDTO>> getInsightsByStatus();

    // Update the status of the insight
    String updateStatus(Long id, Status status);
}
