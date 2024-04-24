package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.AlertDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@Tag(
        name = "Alert REST API",
        description = "CRUD REST API for Alerts"
)
public class AlertController {

    // Create Alert
    @Operation(
            summary = "Create Alert",
            description = "Creates a New Alert"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Alert created successfully"
    )
    @PostMapping
    public ResponseEntity<String> createAlert(@RequestBody AlertDTO alert) {
        return new ResponseEntity<>("Alert created successfully", HttpStatus.OK);
    }


    // Get All Alerts Rest API
    @Operation(
            summary = "Get All Alerts",
            description = "Gets All Alerts"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Alerts fetched successfully"
    )
    @GetMapping
    public List<AlertDTO> getAllAlerts() {
        AlertDTO alert1 = new AlertDTO();
        alert1.setId(1L);
        alert1.setName("Reconfigure virtual floor");
        alert1.setDescription(""); // Assuming empty since not specified
        alert1.setType("virtual floor");
        alert1.setStatus("waiting");
        alert1.setSeverity("low");
        alert1.setCreatedAt(LocalDateTime.of(2024, 4, 13, 12, 10, 11));
        alert1.setUpdatedAt(LocalDateTime.of(2024, 4, 13, 12, 10, 11));

        AlertDTO alert2 = new AlertDTO();
        alert2.setId(2L);
        alert2.setName("Chat with agent");
        alert2.setDescription(""); // Assuming empty since not specified
        alert2.setType("agent");
        alert2.setStatus("waiting");
        alert2.setSeverity("moderate");
        alert2.setCreatedAt(LocalDateTime.of(2024, 4, 13, 12, 12, 11));
        alert2.setUpdatedAt(LocalDateTime.of(2024, 4, 13, 12, 20, 11));

        AlertDTO alert3 = new AlertDTO();
        alert3.setId(3L);
        alert3.setName("Agent call take");
        alert3.setDescription(""); // Assuming empty since not specified
        alert3.setType("agent");
        alert3.setStatus("waiting");
        alert3.setSeverity("high");
        alert3.setCreatedAt(LocalDateTime.of(2024, 4, 13, 15, 10, 11));
        alert3.setUpdatedAt(LocalDateTime.of(2024, 4, 13, 15, 15, 11));

        return Arrays.asList(alert1, alert2, alert3);
    }

    // Get Alert by ID API
    @Operation(
            summary = "Get Alert by ID",
            description = "Gets a specific alert by its ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Alert fetched successfully"
    )
    @GetMapping("/{alertId}")
    public AlertDTO getAlertById(@PathVariable Long alertId) {
        AlertDTO alert = new AlertDTO();

        alert.setId(0L);
        alert.setMetricID(alertId);
        alert.setName("Low Service Level");
        alert.setDescription("Service level is low");
        alert.setType("Service Level");
        alert.setStatus("Open");
        alert.setSeverity(AlertDTO.severity.LOW);
        alert.setMinThreshold(90L);
        alert.setMaxThreshold(100L);
        alert.setSupervisor("John Doe");
        alert.setAgent("Jane Doe");
        alert.setCreatedAt(LocalDateTime.parse("2007-12-03T10:15:30"));
        alert.setUpdatedAt(LocalDateTime.parse("2007-12-03T10:15:31"));

        return alert;
    }
}
