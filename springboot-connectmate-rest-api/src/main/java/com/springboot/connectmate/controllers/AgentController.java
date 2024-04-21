package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.AgentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
