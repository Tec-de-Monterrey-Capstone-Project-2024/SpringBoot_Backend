package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.Metric.MetricDTO;
import com.springboot.connectmate.enums.ConnectMetricCode;
import com.springboot.connectmate.services.MetricService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
@Tag(
        name = "Connectmate Metrics REST API",
        description = "CRUD REST API for Connectmate Metrics"
)
public class MetricController {

    private final MetricService metricService;

    @Autowired
    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @Operation(summary = "Get all metrics", description = "Retrieve all metrics")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping()
    public ResponseEntity<List<MetricDTO>> getAllMetrics() {
        List<MetricDTO> metrics = metricService.getAllConnectMateMetrics();
        return ResponseEntity.ok(metrics);
    }

    @Operation(summary = "Get one metric", description = "Retrieve one metrics")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping("/{code}")
    public ResponseEntity<MetricDTO> getMetric(@PathVariable ConnectMetricCode code) {
        return ResponseEntity.ok(metricService.getConnectMateMetricByCode(code));
    }

    @Operation(summary = "Set thresholds and target", description = "Set thresholds and target")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @PostMapping("/{code}/setThresholdsAndTarget")
    public ResponseEntity<MetricDTO> setThresholdsAndTarget(@PathVariable(name = "code") ConnectMetricCode code,
                                                         @RequestParam(required = false) Double minThreshold,
                                                         @RequestParam(required = false) Double maxThreshold,
                                                         @RequestParam(required = false) Double targetValue) {
        return ResponseEntity.ok(metricService.setThresholdsAndTarget(code, minThreshold, maxThreshold, targetValue));
    }






}
