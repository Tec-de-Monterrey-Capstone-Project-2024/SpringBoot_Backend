package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.AmazonConnect.ConnectAgentMetricDTO;
import com.springboot.connectmate.dtos.AmazonConnect.ConnectQueueMetricDTO;
import com.springboot.connectmate.services.AmazonConnectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/amazon-connect")
@Tag(
        name = "Amazon Connect Metrics REST API",
        description = "CRUD REST API for Amazon Connect Metrics"
)
public class ConnectMetricController {

    private final AmazonConnectService amazonConnectService;

    @Autowired
    public ConnectMetricController(AmazonConnectService amazonConnectService) {
        this.amazonConnectService = amazonConnectService;
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ConnectQueueMetricDTO.class))),
            description = "List of metrics for a given queue and instance fetched successfully."
    )
    @Operation(
            summary = "Get all queue metrics of a particular queue.",
            description = "Get Amazon Connect queue metrics by instance ID and queue ID."
    )
    @GetMapping("/instances/queue-metrics")
    public ResponseEntity<ConnectQueueMetricDTO> getQueueMetrics(
            @RequestParam(name = "instanceArn") String instanceArn,
            @RequestParam(name = "queueId") String queueId
    ){
        return ResponseEntity.ok(amazonConnectService.getQueueMetrics(instanceArn, queueId));
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ConnectAgentMetricDTO.class))),
            description = "List of metrics for a given instance and agent fetched successfully"
    )
    @Operation(
            summary = "Get all agent metrics",
            description = "Get Amazon Connect metrics by instanceARN and agentId."
    )
    @GetMapping("/instances/agent-metrics")
    public ResponseEntity<ConnectAgentMetricDTO> getAgentMetrics(
            @RequestParam(name = "instanceArn") String instanceArn,
            @RequestParam(name = "agentId") String agentId) {
        return ResponseEntity.ok(amazonConnectService.getAgentMetrics(instanceArn, agentId));
    }
}
