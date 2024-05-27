package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import com.springboot.connectmate.repositories.MetricRepository;
import com.springboot.connectmate.repositories.ThresholdBreachInsightRepository;
import com.springboot.connectmate.services.BedrockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetricCheckService {

    private final MetricRepository metricRepository;
    private final BedrockService bedrockService;
    private final ThresholdBreachInsightRepository thresholdBreachInsightRepository;

    @Autowired
    public MetricCheckService(MetricRepository metricRepository,
                              BedrockService bedrockService,
                              ThresholdBreachInsightRepository thresholdBreachInsightRepository){
        this.metricRepository = metricRepository;
        this.bedrockService = bedrockService;
        this.thresholdBreachInsightRepository = thresholdBreachInsightRepository;
    }


    private Boolean hasBreachedOrIsOnTarget(Metric metric, Double metricValue ) {
        // If the API does not return the metric value, return false
        if (metricValue == null) return false;

        return (metric.getMinimumThresholdValue() != null && metricValue < metric.getMinimumThresholdValue())
                || metricValue.equals(metric.getTargetValue())
                || (metric.getMaximumThresholdValue() != null && metricValue > metric.getMaximumThresholdValue());
    }

    // Need connect metrics and threshold repositories for getting the metrics and thresholds
    @Scheduled(fixedRate = 10000, initialDelay = 10000) // Run every 10 seconds with an initial delay of 10 seconds
    public void checkThresholdBreaches() {
        System.out.println("Checking for threshold breaches...");

        // Get all metrics from the database
        List<Metric> metrics = metricRepository.findAll();

        // Iterate over each metric
        for (Metric metric : metrics) {
            /**
             * TODO: Fetch the actual value of the metric from the INSTANCE, QUEUE, and AGENT
             */
            // Fetch from an INSTANCE (General Metric)
            String instanceId = "INSTANCE_ID"; // Fetch the instance ID from the metric
            Double generalMetricValue = 0.0; // Fetch the actual value of the metric from the INSTANCE
            // Fetch from a QUEUE (Queue Metric)
            String queueId = "QUEUE_ID"; // Fetch the queue ID from the metric
            Double queueMetricValue = 0.0; // Fetch the actual value of the metric from the QUEUE
            // Fetch from an AGENT (Agent Metric)
            String agentId = "AGENT_ID"; // Fetch the agent ID from the metric
            Double agentMetricValue = 0.0; // Fetch the actual value of the metric from the AGENT

            /**
             *  Check if the metric has breached or is on target on any of the three levels
             */
            // Check the general metric value
            Boolean instanceNeedsInsight = hasBreachedOrIsOnTarget(metric, generalMetricValue);
            // Check the queue metric value
            Boolean queueNeedsInsight = hasBreachedOrIsOnTarget(metric, queueMetricValue);
            // Check the agent metric value
            Boolean agentNeedsInsight = hasBreachedOrIsOnTarget(metric, agentMetricValue);

            /**
             *  Generate insights if the metric has breached or is on target
             *  Delete the insight if the metric is no longer breached or on target
             */
            // Handle insights for INSTANCE
            Optional<ThresholdBreachInsight> existingInstanceInsight = thresholdBreachInsightRepository
                    .findByMetricCodeAndConnectItemId(metric, instanceId);
            if (instanceNeedsInsight) {
                // If insight is needed and does not exist, generate the insight
                if (existingInstanceInsight.isEmpty()) {
                    bedrockService.generateInsight(metric, generalMetricValue, ConnectMetricType.INSTANCE, instanceId);
                }
            } else {
                // If insight is not needed and exists, delete the insight
                existingInstanceInsight.ifPresent(thresholdBreachInsightRepository::delete);
            }

            // Handle insights for QUEUE
            Optional<ThresholdBreachInsight> existingQueueInsight = thresholdBreachInsightRepository
                    .findByMetricCodeAndConnectItemId(metric, queueId);
            if (queueNeedsInsight) {
                // If insight is needed and does not exist, generate the insight
                if (existingQueueInsight.isEmpty()) {
                    bedrockService.generateInsight(metric, queueMetricValue, ConnectMetricType.QUEUE, queueId);
                }
            } else {
                // If insight is not needed and exists, delete the insight
                existingQueueInsight.ifPresent(thresholdBreachInsightRepository::delete);
            }

            // Handle insights for AGENT
            Optional<ThresholdBreachInsight> existingAgentInsight = thresholdBreachInsightRepository
                    .findByMetricCodeAndConnectItemId(metric, agentId);
            if (agentNeedsInsight) {
                if (existingAgentInsight.isEmpty()) {
                    // If insight is needed and does not exist, generate the insight
                    bedrockService.generateInsight(metric, agentMetricValue, ConnectMetricType.AGENT, agentId);
                }
            } else {
                // If insight is not needed and exists, delete the insight
                existingAgentInsight.ifPresent(thresholdBreachInsightRepository::delete);
            }
        }
    }
}