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

            // Check if the metric has breached or is on target on any of the three levels
            // Check the general metric value
            Boolean instanceNeedsInsight = hasBreachedOrIsOnTarget(metric, generalMetricValue);
            // Check the queue metric value
            Boolean queueNeedsInsight = hasBreachedOrIsOnTarget(metric, queueMetricValue);
            // Check the agent metric value
            Boolean agentNeedsInsight = hasBreachedOrIsOnTarget(metric, agentMetricValue);

            /**
             *  * Contexto, Este servicio checa si una metrica esta en breach o en target, si esta en breach
             *              * genera un insight, si ya no esta en breach, borra el insight
             * TODO: Fede Necesitas Implementar la Logica para borrar los insights que ya no esten en breach
             * Si la flag de ___NeedsInsight es false Y hay un registro asocdiado a esa metrica y a ese item
             * Borralo, eso quiere decir que la brecha se arreglo y ya no es necesario tener el insight
             *literalmente es hacer otros ifs o agregarle elses a las clauses de abajo.
             *
             */

            // Generate insights for the breached or on-target metrics
            if (instanceNeedsInsight) {
                // Check if a threshold breach insight already exists for the INSTANCE
                Optional<ThresholdBreachInsight> existingInsight =  thresholdBreachInsightRepository
                        .findByMetricCodeAndConnectItemId(metric, instanceId);
                // If it does not exist, generate an insight for the INSTANCE
                if (existingInsight.isEmpty()) {
                    bedrockService.generateInsight(metric, generalMetricValue, ConnectMetricType.INSTANCE, instanceId);
                }
            }
            if (queueNeedsInsight) {
                // Check if a threshold breach insight already exists for the QUEUE
                Optional<ThresholdBreachInsight> existingInsight =  thresholdBreachInsightRepository
                        .findByMetricCodeAndConnectItemId(metric, queueId);
                // If it does not exist, generate an insight for the QUEUE
                if (existingInsight.isEmpty()) {
                    bedrockService.generateInsight(metric, queueMetricValue, ConnectMetricType.QUEUE, queueId);
                }
            }
            if (agentNeedsInsight) {
                // Check if a threshold breach insight already exists for the AGENT
                Optional<ThresholdBreachInsight> existingInsight =  thresholdBreachInsightRepository
                        .findByMetricCodeAndConnectItemId(metric, agentId);
                // If it does not exist, generate an insight for the AGENT
                if (existingInsight.isEmpty()) {
                    bedrockService.generateInsight(metric, agentMetricValue, ConnectMetricType.AGENT, agentId);
                }
            }



        }
    }
}
