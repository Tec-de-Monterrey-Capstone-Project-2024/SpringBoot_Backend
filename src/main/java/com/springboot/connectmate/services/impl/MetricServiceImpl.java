package com.springboot.connectmate.services.impl;


import com.springboot.connectmate.dtos.Metric.MetricDTO;
import com.springboot.connectmate.enums.ConnectMetricCode;
import com.springboot.connectmate.exceptions.ResourceNotFoundException;
import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.repositories.MetricRepository;
import com.springboot.connectmate.services.MetricService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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
    public List<Metric> getAllMetrics() {
        return metricRepository.findAll();
    }

    @Override
    public Metric getMetricByCode(ConnectMetricCode code) {
        return metricRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Metric", "code", code.toString()));
    }

    @Override
    public List<MetricDTO> getAllConnectMateMetrics() {
        List<Metric> metrics = metricRepository.findAll();
        return metrics.stream()
                .map(metric -> mapper.map(metric, MetricDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MetricDTO getConnectMateMetricByCode(ConnectMetricCode code) {
        Metric metric = metricRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Metric", "code", code.toString()));
        return mapper.map(metric, MetricDTO.class);
    }

    @Override
    public MetricDTO setThresholdsAndTarget(ConnectMetricCode code, Double minThreshold, Double maxThreshold, Double targetValue) {
        Metric metric = metricRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Metric", "code", code.toString()));

        // Check if the thresholds are within the allowed bounds
        double lowerBound = code.getLowerBound();
        double upperBound = code.getUpperBound();
        if (minThreshold != null && minThreshold < lowerBound) {
            throw new IllegalArgumentException("Minimum threshold is below the allowed lower bound.");
        }

        if (maxThreshold != null && maxThreshold > upperBound) {
            throw new IllegalArgumentException("Maximum threshold is above the allowed upper bound.");
        }

        // Update the metric
        metric.setMinimumThresholdValue(minThreshold);
        metric.setMaximumThresholdValue(maxThreshold);
        metric.setTargetValue(targetValue != null ? targetValue : code.getDefaultTargetValue());
        metricRepository.save(metric);
        return mapper.map(metric, MetricDTO.class);
    }
}
