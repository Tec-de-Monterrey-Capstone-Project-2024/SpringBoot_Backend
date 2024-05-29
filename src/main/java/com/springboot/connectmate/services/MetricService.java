package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Metric.MetricDTO;
import com.springboot.connectmate.enums.ConnectMetricCode;
import com.springboot.connectmate.models.Metric;

import java.util.List;

public interface MetricService {

    // For Internal Use Only
    List<Metric> getAllAmazonConnectMetrics();

    // Exposed Services
    List<MetricDTO> getAllConnectMateMetrics();
    MetricDTO getConnectMateMetricByCode(ConnectMetricCode code);
    MetricDTO setThresholdsAndTarget(
            ConnectMetricCode code,
            Double minThreshold,
            Double maxThreshold,
            Double targetValue
    );

}