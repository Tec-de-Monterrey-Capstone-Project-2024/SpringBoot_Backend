package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.OldDTOS.OldAlertDTO;
import com.springboot.connectmate.services.AlertService;
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

    private final AlertService alertService;

    @Autowired
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

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
    public ResponseEntity<String> createAlert(@RequestBody OldAlertDTO alert) {
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
    public List<OldAlertDTO> getAllAlerts() {
        return List.of(new OldAlertDTO());
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
    public OldAlertDTO getAlertById(@PathVariable Long alertId) {
        OldAlertDTO alert = new OldAlertDTO();

        alert.setId(0L);
        alert.setMetricID(alertId);
        alert.setName("Low Service Level");
        alert.setDescription("Service level is low");
        alert.setType("Service Level");
        alert.setStatus("Open");
        alert.setSeverity(OldAlertDTO.severity.LOW);
        alert.setMinThreshold(90L);
        alert.setMaxThreshold(100L);
        alert.setSupervisor("John Doe");
        alert.setAgent("Jane Doe");
        alert.setCreatedAt(LocalDateTime.parse("2007-12-03T10:15:30"));
        alert.setUpdatedAt(LocalDateTime.parse("2007-12-03T10:15:31"));

        return alert;
    }
}
