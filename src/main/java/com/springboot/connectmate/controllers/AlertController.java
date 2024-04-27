package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.Alert.AlertDTO;
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
@RequestMapping("/api")
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

    @Operation(summary = "Get All Alerts", description = "Gets All Alerts")
    @ApiResponse(responseCode = "200", description = "Alerts fetched successfully")
    @GetMapping("/threshold-breaches")
    public ResponseEntity<List<AlertDTO>> getAllAlerts() {
        // Get all alerts from the service layer and convert them to AlertDTO
        List<AlertDTO> alerts = alertService.getAllAlerts().stream().map(alert -> new AlertDTO(alert)).toList();
        return ResponseEntity.ok(alerts);
    }

    @Operation(summary = "Get Alert by ID", description = "Gets a specific alert by its ID")
    @ApiResponse(responseCode = "200", description = "Alert fetched successfully")
    @GetMapping("/alerts/{id}")
    public ResponseEntity<AlertDTO> getAlertById(@PathVariable Long id) {
        // Get the alert by ID from the service layer and convert it to AlertDTO
        AlertDTO alert = new AlertDTO(alertService.getAlertById(id));
        return ResponseEntity.ok(alert);
    }

    @Operation(summary = "Create Alert", description = "Creates a new Alert")
    @ApiResponse(responseCode = "201", description = "Alert created successfully")
    @PostMapping("/alerts")
    public ResponseEntity<AlertDTO> createAlert(@RequestBody AlertDTO alertDTO) {
        // Create the alert from the AlertDTO and return the created AlertDTO
        AlertDTO createdAlert = new AlertDTO(alertService.createAlert(alertDTO.mapToAlert()));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAlert);
    }

    @Operation(summary = "Update Alert", description = "Updates an existing Alert")
    @ApiResponse(responseCode = "200", description = "Alert updated successfully")
    @PutMapping("/alerts/{id}")
    public ResponseEntity<AlertDTO> updateAlert(@PathVariable Long id, @RequestBody AlertDTO alertDTO) {
        // Set the ID of the alertDTO and update the alert in the service layer
        alertDTO.setId(id);
        AlertDTO updatedAlert = new AlertDTO(alertService.updateAlert(alertDTO.mapToAlert()));
        return ResponseEntity.ok(updatedAlert);
    }

    @Operation(summary = "Delete Alert", description = "Deletes an existing Alert")
    @ApiResponse(responseCode = "204", description = "Alert deleted successfully")
    @DeleteMapping("/alerts/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long id) {
        alertService.deleteAlert(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(AlertNotFoundException.class)
public ResponseEntity<String> handleNotFound(AlertNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
}

@ExceptionHandler(Exception.class)
public ResponseEntity<String> handleGeneralException(Exception ex) {
    // Log the exception details for debugging purposes
    logger.error("An unexpected error occurred", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
}

}