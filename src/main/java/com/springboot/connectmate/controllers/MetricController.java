package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.Metric.MetricDTO;
import com.springboot.connectmate.dtos.Metric.MetricThresholdsDTO;
import com.springboot.connectmate.services.MetricService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.springboot.connectmate.dtos.Metric.MetricThresholdsDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/metrics")
@Tag(
        name = "Metric REST API",
        description = "CRUD REST API for Metrics"
)
public class MetricController {

    private final MetricService metricService;

    @Autowired
    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    // Get average metrics (average of all agents) for the Call Center API
    @Operation(
            summary = "Get the metrics for the Call Center in general",
            description = "With the individual agent metrics in contact " +
                    "center the API obtain the average of each metric."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Metrics fetched successfully"
    )
    @GetMapping("/agents")
    public List<MetricDTO> getContactCenterMetrics(){
        return metricService.getContactCenterMetrics();
    }


    // Reset the thresholds of a metric
    @PatchMapping("/{metricId}/remove-thresholds")
    public ResponseEntity<MetricThresholdsDTO> removeThresholds(@PathVariable Long metricId) {
    BigDecimal zeroThreshold = BigDecimal.ZERO; 
    BigDecimal maxThreshold = new BigDecimal("9999999"); 

    MetricThresholdsDTO updatedMetric = metricService.updateMetricThresholds(metricId, zeroThreshold, maxThreshold);
    return ResponseEntity.ok(updatedMetric);
}

    
}
