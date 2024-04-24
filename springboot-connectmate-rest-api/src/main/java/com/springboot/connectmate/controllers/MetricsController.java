package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.AgentMetricsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 The MetricsController is responsible for handling API requests related to the metrics of agents.
 It uses {@link AgentMetricsDTO} to transfer metric data to and from the API layer.
 */
@Tag(name = "MetricsController", description = "The Metrics API")
@RestController
@RequestMapping("/agents")
public class MetricsController {
/**
     Retrieves agent metrics, including service level, occupancy, schedule adherence, 
      abandoned calls, first call resolution, and average answer speed.
     
      @return AgentMetricsDTO containing various metrics.
     */
    @Operation(summary = "Get agent metrics", 
               description = "Retrieve a list of metrics related to agent performance.")
    @GetMapping("/metrics")
    public AgentMetricsDTO getMetrics() {
        AgentMetricsDTO metricsDTO = new AgentMetricsDTO();
        metricsDTO.setServiceLevel(95.5);
        metricsDTO.setOccupancy(80.0);
        metricsDTO.setScheduleAdherence(92.3);
        metricsDTO.setAbandonedCalls(3.0);
        metricsDTO.setFirstCallResolution(75.0);
        metricsDTO.setAverageAnswerSpeed(20.5);

        return metricsDTO;
    }
}
