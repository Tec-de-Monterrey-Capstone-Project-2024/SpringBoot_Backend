package com.springboot.connectmate.services;

import com.springboot.connectmate.enums.ConnectMetricCode;

public interface MetricGeneratorService {

    double generateRandomMetricValue(ConnectMetricCode metricCode);
}
