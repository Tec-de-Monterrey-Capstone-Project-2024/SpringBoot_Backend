package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachFieldsDTO;
import com.springboot.connectmate.enums.ConnectMetricCode;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import com.springboot.connectmate.repositories.MetricRepository;
import com.springboot.connectmate.repositories.ThresholdBreachInsightRepository;
import com.springboot.connectmate.services.BedrockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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


    /**
     * Check if the metric has breached or is on target
     */
    private Boolean hasBreachedOrIsOnTarget(Metric metric, Double metricValue ) {
        // If the API does not return the metric value, return false
        if (metricValue == null) return false;

        return (metric.getMinimumThresholdValue() != null && metricValue < metric.getMinimumThresholdValue())
                || metricValue.equals(metric.getTargetValue())
                || (metric.getMaximumThresholdValue() != null && metricValue > metric.getMaximumThresholdValue());
    }

    /**
    * Check for threshold breaches
    */
    @Scheduled(fixedRate = 10000, initialDelay = 10000) // Run every 10 seconds with an initial delay of 10 seconds
    public void checkThresholdBreaches() {
        System.out.println("Checking for threshold breaches...");

        // Get all metrics from the database
        List<Metric> metrics = metricRepository.findAll();

        // Iterate over each metric
        for (Metric metric : metrics) {
            // Get the code of the metric
            ConnectMetricCode metricCode = metric.getCode();

            // Determine the types for this metric
            boolean isInstanceMetric = metricCode.getMetricTypes().contains(ConnectMetricType.INSTANCE);
            boolean isQueueMetric = metricCode.getMetricTypes().contains(ConnectMetricType.QUEUE);
            boolean isAgentMetric = metricCode.getMetricTypes().contains(ConnectMetricType.AGENT);

            // Fetch the actual value of the metric from all Connect Items
            if (isInstanceMetric) {
                /**
                 * TODO: Fetch all Generic Metrics from the INSTANCE (list Metrics from main instances)
                 */
                // Fetch from an INSTANCE (General Metric)
                String instanceName = "connectmate"; // Fetch the instance name from the metric
                String instanceId = "7c78bd60-4a9f-40e5-b461-b7a0dfaad848"; // Fetch the instance ID from the metric
                Double instanceMetricValue = null; // Fetch the actual value of the metric from the INSTANCE

                /**
                 *  Check if the metric has breached or is on target on any of the three levels
                 */
                Boolean instanceNeedsInsight = hasBreachedOrIsOnTarget(metric, instanceMetricValue);

                /**
                 *  Generate insights if the metric has breached or is on target
                 *  Delete the insight if the metric is no longer breached or on target
                 */
                // Get the existing insight for the instance (if any)
                Optional<ThresholdBreachInsight> existingInstanceInsight = thresholdBreachInsightRepository
                        .findByMetricCodeAndConnectItemId(metric, instanceId);

                // ThresholdBreachFieldsDTO object to store the threshold breach data
                ThresholdBreachFieldsDTO thresholdBreachFields = new ThresholdBreachFieldsDTO(
                        instanceId,
                        ConnectMetricType.INSTANCE,
                        instanceMetricValue,
                        LocalDateTime.now()
                );

                if (instanceNeedsInsight) {
                    // If insight is needed and does not exist, generate the insight
                    if (existingInstanceInsight.isEmpty()) {
                        System.out.println("Generating insight for INSTANCE: " + instanceName);
                        bedrockService.generateInsight(metric, thresholdBreachFields, instanceName);
                    }
                } else {
                    // If insight is not needed and exists, delete the insight
                    System.out.println("Deleting insight for INSTANCE: " + instanceName);
                    existingInstanceInsight.ifPresent(thresholdBreachInsightRepository::delete);
                }
            }

            // Fetch the actual value of the metric from the QUEUE
            if (isQueueMetric) {
                /**
                 * TODO: Fetch all QUEUE Metrics from ALL QUEUES (list all metrics from all queues)
                 */
                // Fetch from a QUEUE (Queue Metric)
                String queueName = "BasicQueue"; // Fetch the queue name from the metric
                String queueId = "f0813607-af92-4a36-91e6-630ababb643c"; // Fetch the queue ID from the metric
                Double queueMetricValue = null; // Fetch the actual value of the metric from the QUEUE

                /**
                 *  Check if the metric has breached or is on target on any of the three levels
                 */
                Boolean queueNeedsInsight = hasBreachedOrIsOnTarget(metric, queueMetricValue);

                /**
                 *  Generate insights if the metric has breached or is on target
                 *  Delete the insight if the metric is no longer breached or on target
                 */

                // Get the existing insight for the queue (if any)
                Optional<ThresholdBreachInsight> existingQueueInsight = thresholdBreachInsightRepository
                        .findByMetricCodeAndConnectItemId(metric, queueId);

                // ThresholdBreachFieldsDTO object to store the threshold breach data
                ThresholdBreachFieldsDTO thresholdBreachFields = new ThresholdBreachFieldsDTO(
                        queueId,
                        ConnectMetricType.QUEUE,
                        queueMetricValue,
                        LocalDateTime.now()
                );

                if (queueNeedsInsight) {
                    // If insight is needed and does not exist, generate the insight
                    if (existingQueueInsight.isEmpty()) {
                        System.out.println("Generating insight for QUEUE: " + queueName);
                        bedrockService.generateInsight(metric, thresholdBreachFields, queueName);
                    }
                } else {
                    // If insight is not needed and exists, delete the insight
                    System.out.println("Deleting insight for QUEUE: " + queueName);
                    existingQueueInsight.ifPresent(thresholdBreachInsightRepository::delete);
                }
            }

            // Fetch the actual value of the metric from the AGENT
            if (isAgentMetric) {
                /**
                 * TODO: Fetch all AGENT Metrics from ALL AGENTS (list all metrics from all agents)
                 */
                // Fetch from an AGENT (Agent Metric)
                String agentName = "aram_connectmate"; // Fetch the agent name from the metric
                String agentId = "ed1ad50d-2ffc-44ad-a565-71f13ad991a5"; // Fetch the agent ID from the metric
                Double agentMetricValue = null; // Fetch the actual value of the metric from the AGENT

                /**
                 *  Check if the metric has breached or is on target on any of the three levels
                 */
                Boolean agentNeedsInsight = hasBreachedOrIsOnTarget(metric, agentMetricValue);

                /**
                 *  Generate insights if the metric has breached or is on target
                 *  Delete the insight if the metric is no longer breached or on target
                 */
                // Get the existing insight for the agent (if any)
                Optional<ThresholdBreachInsight> existingAgentInsight = thresholdBreachInsightRepository
                        .findByMetricCodeAndConnectItemId(metric, agentId);

                // ThresholdBreachFieldsDTO object to store the threshold breach data
                ThresholdBreachFieldsDTO thresholdBreachFields = new ThresholdBreachFieldsDTO(
                        agentId,
                        ConnectMetricType.AGENT,
                        agentMetricValue,
                        LocalDateTime.now()
                );

                if (agentNeedsInsight) {
                    if (existingAgentInsight.isEmpty()) {
                        // If insight is needed and does not exist, generate the insight
                        System.out.println("Generating insight for AGENT: " + agentName);
                        bedrockService.generateInsight(metric, thresholdBreachFields, agentName);
                    }
                } else {
                    // If insight is not needed and exists, delete the insight
                    System.out.println("Deleting insight for AGENT: " + agentName);
                    existingAgentInsight.ifPresent(thresholdBreachInsightRepository::delete);
                }
            }
        }
    }
}