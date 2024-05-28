package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.Update.UpdateThresholdMetricDTO;
import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.services.AmazonConnectService;
import com.springboot.connectmate.services.MetricService;
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
    private final MetricService metricService;

    @Autowired
    public ConnectMetricController(AmazonConnectService amazonConnectService, MetricService metricService) {
        this.amazonConnectService = amazonConnectService;
        this.metricService = metricService;
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
                    schema = @Schema(implementation = Metric.class)),
            description = "Thresholds updated successfully"
    )
    @Operation(
            summary = "Update thresholds",
            description = "Update the thresholds for a given metric by code."
    )
    @PatchMapping("/{code}/thresholds")
    public ResponseEntity<Metric> updateThresholds(
            @PathVariable(name = "code") String code,
            @RequestBody UpdateThresholdMetricDTO updateThresholdMetricDTO) {
        return ResponseEntity.ok(metricService.updateThresholds(code, updateThresholdMetricDTO));
    }
}
