package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.SupervisorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health-check")
@Tag(
        name = "Health Check REST API",
        description = "CRUD REST API for Health Check"
)
public class HealthCheckController {

    // Get Supervisor by ID Rest API
    @Operation(
            summary = "Get Status",
            description = "Fetches the current status of the application"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Health Check"
    )
    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Service is operational as of " + java.time.LocalDateTime.now());
    }

    // Get Supervisor by ID Rest API
    @Operation(
            summary = "Get Version",
            description = "Fetches the current version of the application"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Health Check"
    )
    @GetMapping("/version")
    public ResponseEntity<String> getVersion() {
        String currentVersion = "0.0.0";
        String deploymentDate = "2024-04-24";
        return ResponseEntity.ok("Current service version: " + currentVersion + ", deployed on: " + deploymentDate);
    }
}
