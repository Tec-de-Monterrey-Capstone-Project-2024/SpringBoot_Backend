package com.springboot.connectmate.controllers;

import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.services.MetricService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Operation(summary = "Get all metrics", description = "Retrieve all metrics from the database")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved metrics")
    @GetMapping("/all")
    public ResponseEntity<List<Metric>> getAllMetrics() {
        List<Metric> metrics = metricService.getAllMetrics();
        return new ResponseEntity<>(metrics, HttpStatus.OK);
    }
}
