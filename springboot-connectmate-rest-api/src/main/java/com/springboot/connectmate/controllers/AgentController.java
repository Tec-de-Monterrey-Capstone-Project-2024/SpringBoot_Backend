package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.AgentDTO;
import com.springboot.connectmate.dtos.AgentMetricsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/agents")
@Tag(
        name = "Agent REST API",
        description = "CRUD REST API for Agents"
)
public class AgentController {

    // Get All Agents Rest API
    @Operation(
            summary = "Get All Agents",
            description = "Get All Agents"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Agents fetched successfully"
    )
    @GetMapping
    public List<AgentDTO> getAllAgents() {
        return List.of(new AgentDTO());
    }

    // Get Agent by ID Rest API
    @Operation(
            summary = "Get Agent by Id",
            description = "Get Agent by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Agent fetched successfully"
    )
    @GetMapping("/{id}")
    public ResponseEntity<AgentDTO> getAgentById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(new AgentDTO());
    }



    @GetMapping("/metrics/{id}")
    public ResponseEntity<Object> getAgentMetricsById(@PathVariable(name = "id") long id) {
        if (id == 1) {
            AgentMetricsDTO response = new AgentMetricsDTO();
            response.setDescription("calls answered within");
            response.setLimitInSeconds(20);
            response.setTimeToAnswerCalls(List.of(
                    new AgentMetricsDTO.CallDetailDTO(1, "12 3456 7890", 5),
                    new AgentMetricsDTO.CallDetailDTO(2, "12 3456 7890", 6),
                    new AgentMetricsDTO.CallDetailDTO(3, "12 3456 7890", 5)
            ));
            return ResponseEntity.ok(response);
        } else {
            // Customize this part as needed, e.g., return a custom error message
            return ResponseEntity.status(404).body(null); // Adjust based on your error handling
        }
    }





}


