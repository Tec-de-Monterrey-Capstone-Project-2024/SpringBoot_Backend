package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.MockMetric.MockMetricDTO;

import java.util.List;

public interface MockMetricService {

    List<MockMetricDTO> getAllInstanceMetrics();
    List<MockMetricDTO> getAllQueueMetrics();
    List<MockMetricDTO> getAllAgentMetrics();



}
