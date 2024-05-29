package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.AmazonConnect.InsightDTO;
import com.springboot.connectmate.dtos.AmazonConnect.ThresholdBreachInsightDTO;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachInsightDetailDTO;
import com.springboot.connectmate.enums.ConnectMetricCode;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.models.Metric;

import java.util.List;


public interface ThresholdBreachInsightService {
    ThresholdBreachInsight generateAndSaveInsight(ThresholdBreachInsightDTO dto, InsightDTO insight);
    ThresholdBreachInsight getInsightByMetricCodeAndConnectItemId(Metric metric, String connectItemId);
    List<ThresholdBreachInsightDetailDTO> getAllInsights();
    List<ThresholdBreachInsightDetailDTO> getInsightsByStatus(Status status);
    List<ThresholdBreachInsightDetailDTO> getInsightsByConnectItemId(String connectItemId);
    List<ThresholdBreachInsightDetailDTO> getInsightsByItemType(ConnectMetricType connectItemType);
    String updateStatus(Long id, Status status);
}
