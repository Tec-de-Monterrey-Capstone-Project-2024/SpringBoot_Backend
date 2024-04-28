package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Metric.MetricDTO;

import java.util.List;

public interface MetricService {
    List<MetricDTO> getContactCenterMetrics();
}
