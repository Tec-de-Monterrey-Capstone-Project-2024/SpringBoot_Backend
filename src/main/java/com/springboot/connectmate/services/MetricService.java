package com.springboot.connectmate.services;

import com.springboot.connectmate.models.Metric;

import java.util.List;

public interface MetricService {
    List<Metric> getAllMetrics();
}