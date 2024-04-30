package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.Alert.AlertDTO;
import com.springboot.connectmate.dtos.OldDTOS.OldAlertDTO;
import com.springboot.connectmate.services.AlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // Get Alert by ID API Rest API
    @Operation(
            summary = "Get Alert by Id",
            description = "Get Alert by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Alert fetched successfully"
    )
    @GetMapping("/{id}")
    public ResponseEntity<AlertDTO> getAlertById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(alertService.getAlertById(id));
    }
    // Get All Threshold Breach Alerts
    @GetMapping("/threshold-breaches")
    @Operation(summary = "Get All Threshold Breach Alerts", description = "Gets all alerts related to threshold breaches")
    @ApiResponse(responseCode = "200", description = "Alerts fetched successfully")
    public ResponseEntity<List<AlertDTO>> getAllThresholdBreachAlerts() {
        List<AlertDTO> alerts = alertService.getAllThresholdBreachAlerts();
        return ResponseEntity.ok(alerts);
    }
}