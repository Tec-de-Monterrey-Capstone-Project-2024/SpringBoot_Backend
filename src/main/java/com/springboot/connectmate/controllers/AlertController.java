package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.AlertDTO;
import com.springboot.connectmate.exceptions.AlertNotFoundException;
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
    public ResponseEntity<AlertDTO> createAlert(@RequestBody AlertDTO alertDTO) {
        AlertDTO createdAlertDTO = alertService.createAlert(alertDTO);
        return new ResponseEntity<>(createdAlertDTO, HttpStatus.CREATED);
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
    public ResponseEntity<List<AlertDTO>> getAllAlerts() {
        List<AlertDTO> alertDTOList = alertService.getAllAlerts();
        return new ResponseEntity<>(alertDTOList, HttpStatus.OK);
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
    public ResponseEntity<AlertDTO> getAlertById(@PathVariable Long alertId) {
        AlertDTO alertDTO;
        try {
            alertDTO = alertService.getAlertById(alertId);
        } catch (AlertNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(alertDTO, HttpStatus.OK);
    }

    // Update Alert
    @Operation(
            summary = "Update Alert",
            description = "Updates an existing alert"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Alert updated successfully"
    )
   @PutMapping("/{alertId}")
    public ResponseEntity<AlertDTO> updateAlert(@PathVariable Long alertId, @RequestBody AlertDTO alertDTO) {
        AlertDTO updatedAlertDTO = alertService.updateAlert(alertId, alertDTO);
        return new ResponseEntity<>(updatedAlertDTO, HttpStatus.OK);
    }

    // Delete Alert
    @Operation(
            summary = "Delete Alert",
            description = "Deletes an existing alert"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Alert deleted successfully"
    )
    @DeleteMapping("/{alertId}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long alertId) {
        alertService.deleteAlert(alertId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}