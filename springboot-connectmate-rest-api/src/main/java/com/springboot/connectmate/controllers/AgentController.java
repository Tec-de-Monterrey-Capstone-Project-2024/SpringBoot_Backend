package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.AgentDTO;
import com.springboot.connectmate.dtos.AgentMetricsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents")
@Tag(name = "Agent REST API", description = "CRUD REST API for Agents")
public class AgentController {

    @GetMapping
    @Operation(summary = "Get All Agents", description = "Retrieve all agents")
    @ApiResponse(responseCode = "200", description = "Agents fetched successfully")
    public ResponseEntity<Object> getAllAgents() {
        // Assuming the data is being fetched from somewhere
        List<AgentDTO> agents = List.of(new AgentDTO()); // Replace with actual fetching logic
        return ResponseEntity.ok(agents);
    }

    @GetMapping("/{agentId}")
    @Operation(summary = "Get Agent by ID", description = "Retrieve an agent by their ID")
    @ApiResponse(responseCode = "200", description = "Agent fetched successfully")
    public ResponseEntity<AgentDTO> getAgentById(@PathVariable("agentId") Long id) {
        // Simulated data fetch
        AgentDTO agent = new AgentDTO(); // Assume this object is fetched and populated
        return ResponseEntity.ok(agent);
    }

    @GetMapping("/metrics/{metricId}")
    @Operation(summary = "Get Metrics for Agent", description = "Retrieve metrics for a specific agent by their ID")
    @ApiResponse(responseCode = "200", description = "Metrics fetched successfully")
    public ResponseEntity<Object> getAgentMetricsById(@PathVariable("metricId") Long id) {
        if (id == 1) {
            AgentMetricsDTO metrics = new AgentMetricsDTO();
            metrics.setDescription("calls answered within");
            metrics.setLimitInSeconds(20);
            metrics.setTimeToAnswerCalls(List.of(
                    new AgentMetricsDTO.CallDetailDTO(1, "12 3456 7890", 5),
                    new AgentMetricsDTO.CallDetailDTO(2, "12 3456 7890", 6),
                    new AgentMetricsDTO.CallDetailDTO(3, "12 3456 7890", 5)
            ));
            return ResponseEntity.ok(metrics);
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if no metrics found
        }
    }
}
