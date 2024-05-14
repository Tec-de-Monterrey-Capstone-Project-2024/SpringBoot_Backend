package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.AmazonConnect.*;
import com.springboot.connectmate.services.AmazonConnectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/amazon-connect")
@Tag(
        name = "Amazon Connect REST API",
        description = "CRUD REST API for Amazon Connect"
)
public class AmazonConnectController {

    private final AmazonConnectService amazonConnectService;

    @Autowired
    public AmazonConnectController(AmazonConnectService amazonConnectService) {
        this.amazonConnectService = amazonConnectService;
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                               array = @ArraySchema(schema = @Schema(implementation = ConnectInstanceDTO.class))),
            description = "List of instances fetched successfully."
    )
    @Operation(
            summary = "Get instances",
            description = "Get instances for an specific Amazon region with a given AWS account"
    )
    @GetMapping("/instances")
    public ResponseEntity<List<ConnectInstanceDTO>> listConnectInstances() {
        return ResponseEntity.ok(amazonConnectService.listConnectInstances());
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                               array = @ArraySchema(schema = @Schema(implementation = ConnectQueueDTO.class))),
            description = "List of queues for a given instance fetched successfully."
    )
    @Operation(
            summary = "Get all queues",
            description = "Get Amazon Connect queues by instance ID"
    )
    @GetMapping("/instances/{instanceId}/queues")
    public ResponseEntity<List<ConnectQueueDTO>> listQueues(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.listQueues(instanceId));
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                               array = @ArraySchema(schema = @Schema(implementation = ConnectUserDTO.class))),
            description = "List of users for a given instance fetched successfully."
    )
    @Operation(
            summary = "Get all users",
            description = "Get Amazon Connect users (supervisors, agents, etc) by instance ID"
    )
    @GetMapping("/instances/{instanceId}/users")
    public ResponseEntity<List<ConnectUserDTO>> listUsers(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.listUsers(instanceId));
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                               array = @ArraySchema(schema = @Schema(implementation = ConnectAgentDTO.class))),
            description = "List of agent statues for a given instance fetched successfully."
    )
    @Operation(
            summary = "Get all agent statuses",
            description = "Get Amazon Connect agent statuses (routable, custom, offline) by instance ID"
    )
    @GetMapping("/instances/{instanceId}/agents")
    public ResponseEntity<List<ConnectAgentDTO>> listAgents(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.listAgents(instanceId));
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                               array = @ArraySchema(schema = @Schema(implementation = String.class))),
            description = "List of historical metrics for a given instance fetched successfully."
    )
    @Operation(
            summary = "Get all historical metrics",
            description = "Get Amazon Connect historical metrics by instance ID (maximum 24 hours)"
    )
    @GetMapping("/instances/{instanceId}/historial-metrics")
    public ResponseEntity<List<String>> getHistoricalMetrics(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.getHistoricalMetrics(instanceId));
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                               array = @ArraySchema(schema = @Schema(implementation = ConnectRoutingProfileDTO.class))),
            description = "List of routing profiles for a given instance fetched successfully."
    )
    @Operation(
            summary = "Get all routing profiles",
            description = "Get Amazon Connect routing profiles by instance ID"
    )
    @GetMapping("/instances/{instanceId}/routing-profiles")
    public ResponseEntity<List<ConnectRoutingProfileDTO>> getRoutingProfiles(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.listRoutingProfiles(instanceId));
    }
}
