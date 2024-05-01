package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Metric.ConnectMetricDTO;
import com.springboot.connectmate.dtos.Metric.MetricDTO;
import com.springboot.connectmate.dtos.Metric.MetricDescriptionDTO;

import java.util.List;

public interface MetricService {
    MetricDescriptionDTO getMetricDescriptionById(Long metricId);
    List<MetricDTO> getContactCenterMetrics();
    List<ConnectMetricDTO> getAgentMetrics(Long agentId);
}
