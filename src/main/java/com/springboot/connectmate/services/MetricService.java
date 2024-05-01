package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Metric.MetricDTO;
import com.springboot.connectmate.dtos.Metric.MetricDescriptionDTO;
import com.springboot.connectmate.enums.MetricCategory;

import com.springboot.connectmate.dtos.Metric.SetThresholdsDTO;

import java.util.List;

public interface MetricService {
    MetricDescriptionDTO getMetricDescriptionById(Long metricId);
    List<MetricDTO> getContactCenterMetrics();

    void removeMetricThresholds(Long userId, Long queueId, MetricCategory code);

    void updateMetricThresholds(SetThresholdsDTO thresholdsDTO);
}
