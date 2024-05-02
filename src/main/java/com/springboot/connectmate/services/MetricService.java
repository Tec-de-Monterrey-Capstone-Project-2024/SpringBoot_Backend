package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Metric.ConnectMetricDTO;
import com.springboot.connectmate.dtos.Metric.MetricDTO;
import com.springboot.connectmate.dtos.Metric.MetricDescriptionDTO;
import com.springboot.connectmate.enums.MetricCategory;
import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.dtos.Metric.MetricThresholdsDTO;

import java.math.BigDecimal;
import java.util.List;

public interface MetricService {
    MetricDescriptionDTO getMetricDescriptionById(Long metricId);
    List<MetricDTO> getContactCenterMetrics();
    MetricThresholdsDTO updateMetricThresholds(Long metricId, BigDecimal minimumThreshold, BigDecimal maximumThreshold);
    List<ConnectMetricDTO> getAgentMetrics(Long agentId);
}
