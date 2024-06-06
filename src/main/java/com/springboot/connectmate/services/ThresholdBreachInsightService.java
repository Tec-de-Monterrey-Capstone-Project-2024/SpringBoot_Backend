package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.ThresholdBreachInsight.*;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.models.Metric;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface ThresholdBreachInsightService {

    // Internal Use Only
    InsightAlertDTO mapToInsightAlertDTO(ThresholdBreachInsight insight);
    Optional<ThresholdBreachInsight> getInsightByMetricCodeAndConnectItemId(Metric metric, String connectItemId);
    void deleteInsightById(Long id);
    ThresholdBreachInsight saveInsight(Metric metric,
                     ThresholdBreachFieldsDTO thresholdBreachData,
                     InsightFieldsDTO insightData
    );

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

    // Get All Alerts
    List<InsightAlertDTO> getAlerts();
}
