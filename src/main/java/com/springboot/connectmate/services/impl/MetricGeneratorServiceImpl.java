package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.Metric.MetricDTO;
import com.springboot.connectmate.enums.ConnectMetricCode;
import com.springboot.connectmate.services.MetricGeneratorService;
import com.springboot.connectmate.services.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MetricGeneratorServiceImpl implements MetricGeneratorService {

    private final MetricService metricService;

    private static final Random random = new Random();

    @Autowired
    public MetricGeneratorServiceImpl(MetricService metricService) {
        this.metricService = metricService;
    }

    public double generateRandomMetricValue(ConnectMetricCode metricCode) {
        // Get Metric Data
        MetricDTO metric = metricService.getConnectMateMetricByCode(metricCode);
        // Metric Limit Data
        double targetValue = metric.getTargetValue();
        Double minimumThresholdValue = metric.getMinimumThresholdValue();
        Double maximumThresholdValue = metric.getMaximumThresholdValue();

        // Calculate Bounds
        double actualLowerBound = metricCode.getLowerBound();
        double actualUpperBound = metricCode.getUpperBound();
        double desiredLowerBound = targetValue - (0.1 * targetValue);
        double desiredUpperBound = targetValue + (0.1 * targetValue);
        double lowerBound = Math.max(actualLowerBound, desiredLowerBound);
        double upperBound = Math.min(actualUpperBound, desiredUpperBound);

        // Limits to use
        double lowerLimit = (minimumThresholdValue != null) ? minimumThresholdValue : lowerBound;
        double upperLimit = (maximumThresholdValue != null) ? maximumThresholdValue : upperBound;

        // Generate Random Value
        // Rules:
        // 1. The generated value should not be equal to the target value
        // 2. The generated value should be within the limits
        // 3. If there are no threshold limits, the generated value should be within the bounds
        double generatedValue;
        do {
            generatedValue = lowerLimit + (upperLimit - lowerLimit) * random.nextDouble();
            generatedValue = Math.round(generatedValue * 100.0) / 100.0;
        } while (Double.compare(generatedValue, targetValue) == 0);

        return generatedValue;
    }


}
