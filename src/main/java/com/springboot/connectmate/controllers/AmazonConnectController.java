package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.AmazonConnect.*;
import com.springboot.connectmate.dtos.AmazonConnect.ConnectUserDataDTO;
import com.springboot.connectmate.services.AmazonConnectService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            description = "List of instances fetched successfully"
    )
    @GetMapping("/instances")
    public ResponseEntity<List<ConnectInstanceDTO>> listConnectInstances() {
        return ResponseEntity.ok(amazonConnectService.listConnectInstances());
    }

    @ApiResponse(
            responseCode = "200",
            description = "Queue list of a given instance fetched successfully"
    )
    @GetMapping("/instances/{instanceId}/queues")
    public ResponseEntity<List<ConnectQueueDTO>> listQueues(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.listQueues(instanceId));
    }

    @ApiResponse(
            responseCode = "200",
            description = "List of users for a given instance fetched successfully"
    )
    @GetMapping("/instances/{instanceId}/users")
    public ResponseEntity<List<ConnectUserDTO>> listUsers(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.listUsers(instanceId));
    }

    @ApiResponse(
            responseCode = "200",
            description = "List of agent statues for a given instance fetched successfully"
    )
    @GetMapping("/instances/{instanceId}/agent-statuses")
    public ResponseEntity<List<ConnectAgentDTO>> listAgents(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.listAgents(instanceId));
    }

    @ApiResponse(
            responseCode = "200",
            description = "List of historical metrics for a given instance fetched successfully"
    )
    @GetMapping("/instances/historical-metrics")
    public ResponseEntity<List<String>> getHistoricalMetricsV2(
        @RequestParam(name = "instanceArn") String instanceArn,
        @RequestParam(name = "queueId") String queueId
    ){
        return ResponseEntity.ok(amazonConnectService.getHistoricalMetricsV2(instanceArn, queueId));
    }

    @ApiResponse(
            responseCode = "200",
            description = "List of historical metrics for a given instance fetched successfully"
    )
    @GetMapping("/instances/{instanceId}/queues/{queueId}/historial-metrics")
    public ResponseEntity<List<String>> getHistoricalMetrics(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.getHistoricalMetrics(instanceId, null));
    }

    @ApiResponse(
            responseCode = "200",
            description = "List of current metrics for a given instance fetched successfully"
    )
    @GetMapping("/instances/current-metrics")
    public ResponseEntity<List<String>> getCurrentMetrics(
            @RequestParam(name = "instanceArn") String instanceArn,
            @RequestParam(name = "queueId") String queueId
            ){
        return ResponseEntity.ok(amazonConnectService.getCurrentMetrics(instanceArn));
    }

    @ApiResponse(
            responseCode = "200",
            description = "List of routing profiles for a given instance fetched successfully"
    )
    @GetMapping("/instances/{instanceId}/routing-profiles")
    public ResponseEntity<List<ConnectRoutingProfileDTO>> getRoutingProfiles(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.listRoutingProfiles(instanceId));
    }

    @GetMapping("/instances/{instanceId}/queue-agents")
    public ResponseEntity<List<ConnectUserDataDTO>> getQueueAgents(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.getCurrentData(instanceId));
    }
}
