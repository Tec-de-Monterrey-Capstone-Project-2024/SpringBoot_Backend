    package com.springboot.connectmate.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.springboot.connectmate.dtos.GeneralMetricsDTO;
import com.springboot.connectmate.services.MetricsService;

@RestController
@RequestMapping("/api/metrics")
@Tag(
        name = "Metric REST API",
        description = "CRUD REST API for Metrics"
)
public class MetricsController {

    private final MetricsService metricsService;

    @Autowired
    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Operation(
            summary = "Update Metrics",
            description = "Update Metrics by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Metrics updated successfully"
    )
    @PutMapping("/{id}")
    public ResponseEntity<GeneralMetricsDTO> updateMetrics(@PathVariable(name = "id") long id, @RequestBody GeneralMetricsDTO metricsDTO) {
        GeneralMetricsDTO updatedMetrics = metricsService.updateMetrics(id, metricsDTO);
        if (updatedMetrics != null) {
            return new ResponseEntity<>(updatedMetrics, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}