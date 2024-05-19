package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.Metric.ThresholdBreachesRequestDTO;
import com.springboot.connectmate.services.MetricsInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/metrics-info")
@Tag(
        name = "Metrics Info REST API",
        description = "CRUD REST API for Metrics Info."
)
public class MetricsInfoController {

    private final MetricsInfoService metricsInfoService;

    @Autowired
    public MetricsInfoController(MetricsInfoService metricsInfoService) {
        this.metricsInfoService = metricsInfoService;
    }

    // Create Threshold Breach from a Metrics Info Id.
    @Operation(
            summary = "Create Threshold Breach",
            description = "Creates a new Threshold breach from a Metrics Info. "
    )
    @ApiResponse(
            responseCode = "201",
            description = "Threshold Breach created successfully"
    )
    @PostMapping
    public ResponseEntity<?> addThresholdBreach(@RequestBody ThresholdBreachesRequestDTO thresholdBreaches){
        metricsInfoService.addThresholdBreaches(thresholdBreaches);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
