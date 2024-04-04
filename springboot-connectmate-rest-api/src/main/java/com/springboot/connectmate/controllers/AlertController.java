package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.AlertDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            description = "Creates a new Alert"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Alert created successfully"
    )
    @PostMapping
    public ResponseEntity<AlertDTO> createSupervisor(@RequestBody AlertDTO alertDTO) {
        return new ResponseEntity<>(alertDTO, HttpStatus.CREATED);
    }
}
