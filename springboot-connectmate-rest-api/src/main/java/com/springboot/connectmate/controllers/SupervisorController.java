package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.SupervisorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.springboot.connectmate.services.SupervisorService;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/supervisors")
@Tag(
        name = "Supervisor REST API",
        description = "CRUD REST API for Supervisors"
)
public class SupervisorController {

    private final SupervisorService supervisorService;

    @Autowired
    public SupervisorController(SupervisorService supervisorService) {
        this.supervisorService = supervisorService;
    }

    // Create Supervisor Rest API
    @Operation(
            summary = "Create Supervisor",
            description = "Creates a new Supervisor"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Supervisor created successfully"
    )
    @PostMapping
    public ResponseEntity<SupervisorDTO> createSupervisor(@RequestBody SupervisorDTO supervisorDTO) {
        return new ResponseEntity<>(supervisorService.createSupervisor(supervisorDTO), HttpStatus.CREATED);
    }

    // Get all Supervisors Rest API
    @Operation(
            summary = "Get All Supervisors",
            description = "Get All Supervisors"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Supervisors fetched successfully"
    )
    @GetMapping
    public List<SupervisorDTO> getAllSupervisors() {
        return supervisorService.getAllSupervisors();
    }


    // Get Supervisor by ID Rest API
    @Operation(
            summary = "Get Supervisor by Id",
            description = "Get Supervisor by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Supervisor fetched successfully"
    )
    @GetMapping("/{id}")
    public ResponseEntity<SupervisorDTO> getSupervisorById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(supervisorService.getSupervisorById(id));
    }

    // Update Supervisor Rest API
    @Operation(
            summary = "Update Supervisor",
            description = "Update Supervisor by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Supervisor updated successfully"
    )
    @PutMapping("/{id}")
    public ResponseEntity<SupervisorDTO> updateSupervisor(@PathVariable(name = "id") long id, @RequestBody SupervisorDTO supervisorDTO) {
        SupervisorDTO supervisorResponse = supervisorService.updateSupervisor(id, supervisorDTO);
        return new ResponseEntity<>(supervisorResponse, HttpStatus.OK);
    }

    // Partial Update Supervisor Rest API
    @Operation(
            summary = "Partial Update Supervisor",
            description = "Partially Update Supervisor by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Supervisor partially updated successfully"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<SupervisorDTO> patchSupervisor(@PathVariable(name = "id") long id, @RequestBody Map<String, Object> fields) {
        SupervisorDTO supervisorResponse = supervisorService.patchSupervisor(id, fields);
        return new ResponseEntity<>(supervisorResponse, HttpStatus.OK);
    }

    // Delete Supervisor Rest API
    @Operation(
            summary = "Delete Supervisor",
            description = "Delete Supervisor by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Supervisor deleted successfully"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupervisor(@PathVariable(name = "id") long id) {
        supervisorService.deleteSupervisor(id);
        return new ResponseEntity<>("Supervisor deleted successfully", HttpStatus.OK);
    }
    // Opcional (Get available Supervisors) **(revisar con equipo)**

    // Get Available Supervisors Rest API
    @Operation(
            summary = "Get Available Supervisors",
            description = "Get Supervisors who are currently available"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Available Supervisors fetched successfully"
    )
    @GetMapping("/available")
    public ResponseEntity<List<SupervisorDTO>> getAvailableSupervisors() {
        List<SupervisorDTO> availableSupervisors = supervisorService.getAvailableSupervisors();
        return ResponseEntity.ok(availableSupervisors);
    }
}

