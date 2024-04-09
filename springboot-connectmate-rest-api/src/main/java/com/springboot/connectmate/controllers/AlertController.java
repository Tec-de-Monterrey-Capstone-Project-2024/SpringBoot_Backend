package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.AlertDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
        return List.of(new AlertDTO());
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
        alert.setSeverity("Low");
        alert.setMinThreshold(90L);
        alert.setMaxThreshold(100L);
        alert.setSupervisor("John Doe");
        alert.setAgent("Jane Doe");
        alert.setCreatedAt(LocalDateTime.parse("2007-12-03T10:15:30"));
        alert.setUpdatedAt(LocalDateTime.parse("2007-12-03T10:15:31"));

        return alert;
    }
}
