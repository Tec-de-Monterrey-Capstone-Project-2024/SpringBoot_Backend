package com.springboot.connectmate.controllers;

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

import java.util.List;

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
                    array = @ArraySchema(schema = @Schema(implementation = String.class))),
            description = "List of historical metrics for a given instance fetched successfully."
    )
    @Operation(
            summary = "Get all historical metrics",
            description = "Get Amazon Connect historical metrics by instance ID (maximum 24 hours)"
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
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = String.class))),
            description = "List of historical metrics for a given instance fetched successfully"
    )
    @GetMapping("/instances/{instanceId}/queues/{queueId}/historial-metrics")
    public ResponseEntity<List<String>> getHistoricalMetrics(
            @PathVariable(name = "instanceId") String instanceId,
            @PathVariable(name = "queueId") String queueId) {
        return ResponseEntity.ok(amazonConnectService.getHistoricalMetrics(instanceId, queueId));
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = String.class))),
            description = "List of current metrics for a given instance fetched successfully"
    )
    @Operation(
            summary = "Get all current metrics",
            description = "Get Amazon Connect current metrics by instanceARN."
    )
    @GetMapping("/instances/current-metrics")
    public ResponseEntity<List<String>> getCurrentMetrics(
            @RequestParam(name = "instanceArn") String instanceArn){
        return ResponseEntity.ok(amazonConnectService.getCurrentMetrics(instanceArn));
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = String.class))),
            description = "List of metrics for a given instance and agent fetched successfully"
    )
    @Operation(
            summary = "Get all agent metrics",
            description = "Get Amazon Connect metrics by instanceARN and agentId."
    )
    @GetMapping("/instances/agent-metrics")
    public ResponseEntity<List<String>> getAgentMetrics(
            @RequestParam(name = "instanceArn") String instanceArn, @RequestParam(name = "agentId") String agentId){
        return ResponseEntity.ok(amazonConnectService.getAgentMetrics(instanceArn, agentId));
    }

}
