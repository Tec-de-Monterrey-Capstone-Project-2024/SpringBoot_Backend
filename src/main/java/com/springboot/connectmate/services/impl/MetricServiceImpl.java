package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.Metric.MetricDTO;
import com.springboot.connectmate.dtos.Update.UpdateThresholdMetricDTO;
import com.springboot.connectmate.enums.ConnectMetricCode;
import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.repositories.MetricRepository;
import com.springboot.connectmate.services.MetricService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MetricServiceImpl implements MetricService {

    private final MetricRepository metricRepository;
    private final ModelMapper mapper;

    @Autowired
    public MetricServiceImpl(MetricRepository metricRepository, ModelMapper mapper) {
        this.metricRepository = metricRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Metric> getAllAmazonConnectMetrics() {
        return metricRepository.findAll();
    }

    @Override
    public List<MetricDTO> getAllConnectMateMetrics() {
        // Assuming a need to convert Metric entities to DTOs
        List<Metric> metrics = metricRepository.findAll();
        return metrics.stream()
                .map(metric -> mapper.map(metric, MetricDTO.class))
                .toList();
    }

    @Override
    public Metric updateThresholds(String code, UpdateThresholdMetricDTO updateThresholdMetricDTO) {
        Optional<Metric> optionalMetric = metricRepository.findById(ConnectMetricCode.valueOf(code));
        if (optionalMetric.isPresent()) {
            Metric metric = optionalMetric.get();
            metric.setMinimumThresholdValue(updateThresholdMetricDTO.getMinimumThresholdValue());
            metric.setMaximumThresholdValue(updateThresholdMetricDTO.getMaximumThresholdValue());
            metric.setTargetValue(updateThresholdMetricDTO.getTargetValue());
            return metricRepository.save(metric);
        } else {
            throw new RuntimeException("Metric not found with code: " + code);
        }
    }

    @Override
    public List<String> getHistoricalMetricsV2(String instanceArn, String queueId) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getHistoricalMetrics(String instanceId, String queueId) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getCurrentMetrics(String instanceArn) {
        return Collections.emptyList();
    }
}
