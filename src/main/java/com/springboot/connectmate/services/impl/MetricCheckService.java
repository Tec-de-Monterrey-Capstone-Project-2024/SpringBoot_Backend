package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import com.springboot.connectmate.repositories.MetricRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricCheckService {

    private final MetricRepository metricRepository;

    @Autowired
    public MetricCheckService(MetricRepository metricRepository, ModelMapper mapper) {
        this.metricRepository = metricRepository;
    }


    private ThresholdBreachInsight generateInsightWithBedrock() {
        ThresholdBreachInsight insight = new ThresholdBreachInsight();
        return insight;
    }

    private void performChecks(Double metricValue, Double minimumThreshold, Double targetValue, Double maximumThreshold, String metricType, Metric metric) {
        if (metricValue != null) {
            if (minimumThreshold != null && metricValue < minimumThreshold) {
                generateInsightWithBedrock();
            }
            if (targetValue != null && metricValue.equals(targetValue)) {
                generateInsightWithBedrock();
            }
            if (maximumThreshold != null && metricValue > maximumThreshold) {
                generateInsightWithBedrock();
            }
        }
    }

    // Need connect metrics and threshold repositories for getting the metrics and thresholds
    @Scheduled(fixedRate = 10000, initialDelay = 10000) // Run every 10 seconds with an initial delay of 10 seconds
    public void checkThresholdBreaches() {
        System.out.println("Checking for threshold breaches...");

        // Get all metrics from the database
        List<Metric> metrics = metricRepository.findAll();

        // Iterate over each metric
        for (Metric metric : metrics) {
            // Fetch the thresholds/target value  associated with the current metric
            Double minimumThresholdValue = metric.getMinimumThresholdValue();
            Double targetValue = metric.getTargetValue();
            Double maximumThresholdValue = metric.getMaximumThresholdValue();

            // Fetch value of the metric from an INSTANCE (General Metric)
            Double generalMetricValue = 0.0; // Fetch the actual value of the metric from the INSTANCE
            // Fetch value of the metric from a QUEUE (Queue Metric)
            Double queueMetricValue = 0.0; // Fetch the actual value of the metric from the QUEUE
            // Fetch value of the metric from an AGENT (Agent Metric)
            Double agentMetricValue = 0.0; // Fetch the actual value of the metric from the AGENT

            // Check the general metric value
            performChecks(generalMetricValue, minimumThresholdValue, targetValue, maximumThresholdValue, "General Metric", metric);

            // Check the queue metric value
            performChecks(queueMetricValue, minimumThresholdValue, targetValue, maximumThresholdValue, "Queue Metric", metric);

            // Check the agent metric value
            performChecks(agentMetricValue, minimumThresholdValue, targetValue, maximumThresholdValue, "Agent Metric", metric);
            
        }
    }



}
