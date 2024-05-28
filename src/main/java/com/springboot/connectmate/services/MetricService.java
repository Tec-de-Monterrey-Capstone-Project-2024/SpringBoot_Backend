package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Metric.MetricDTO;
import com.springboot.connectmate.models.Metric;

import java.util.List;

public interface MetricService {

    List<Metric> getAllAmazonConnectMetrics();
    List<MetricDTO> getAllConnectMateMetrics();

}