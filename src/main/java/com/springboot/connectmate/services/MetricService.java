package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Metric.MetricDescriptionDTO;

public interface MetricService {
    MetricDescriptionDTO getMetricDescriptionById(Long metricId);
}
