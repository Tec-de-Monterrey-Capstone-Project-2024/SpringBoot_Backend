package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.AlertDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    // Create Alert Rest API
    @Operation(
            summary = "Create Alert",
            description = "Creates a New Alert"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Alert created successfully"
    )
    @PostMapping
    public ResponseEntity<AlertDTO> createSupervisor(@RequestBody AlertDTO alertDTO) {
        return new ResponseEntity<>(alertDTO, HttpStatus.CREATED);
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
        return new AlertDTO();
    }

    @Operation(
            summary = "Delete Alert by ID",
            description = "Delete a specific alert by its ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Alert deleted successfully"
    )
    @DeleteMapping("/{alertId}")
    public ResponseEntity<String> deleteAlert(@PathVariable(name = "alertId") long id) {
        return ResponseEntity.ok("");
    }

    @Operation(
            summary = "Modify Alert by ID",
            description = "Modified a specific alert by its ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Alert modified successfully"
    )
    @PutMapping("/{alertId}")
    public ResponseEntity<AlertDTO> putAlert(@RequestBody AlertDTO alertDTO) {
        return new ResponseEntity<>(alertDTO, HttpStatus.OK);
    }


}
