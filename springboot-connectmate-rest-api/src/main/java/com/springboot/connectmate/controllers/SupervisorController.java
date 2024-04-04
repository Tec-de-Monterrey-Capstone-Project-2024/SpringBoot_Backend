package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.SupervisorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.springboot.connectmate.services.SupervisorService;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/supervisors")
@Tag(
        name = "Supervisor REST API",
        description = "CRUD REST API for Supervisors"
)
public class SupervisorController {

    private SupervisorService supervisorService;

    //No need for @Autowired
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
            summary = "Get all Supervisors",
            description = "Get all Supervisors"
    )
    @ApiResponse(
            responseCode = "200",
            description = "List of all Supervisors"
    )
    @GetMapping
    public List<SupervisorDTO> getAllSupervisors() {
        return supervisorService.getAllSupervisors();
    }

    // Get Supervisor by Id Rest API
    @Operation(
            summary = "Get Supervisor by Id",
            description = "Get Supervisor by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Supervisor found"
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

}
