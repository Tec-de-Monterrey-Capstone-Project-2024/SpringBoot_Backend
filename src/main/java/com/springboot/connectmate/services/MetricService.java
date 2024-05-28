package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Metric.MetricDTO;
import com.springboot.connectmate.dtos.Update.UpdateThresholdMetricDTO;
import com.springboot.connectmate.models.Metric;

import java.util.List;

public interface MetricService {

    List<Metric> getAllAmazonConnectMetrics();
    List<MetricDTO> getAllConnectMateMetrics();

    List<Metric> getAllMetrics();

    Metric updateThresholds(String code, UpdateThresholdMetricDTO updateThresholdMetricDTO);

    List<String> getHistoricalMetricsV2(String instanceArn, String queueId);

    List<String> getHistoricalMetrics(String instanceId, String queueId);

    List<String> getCurrentMetrics(String instanceArn);
}