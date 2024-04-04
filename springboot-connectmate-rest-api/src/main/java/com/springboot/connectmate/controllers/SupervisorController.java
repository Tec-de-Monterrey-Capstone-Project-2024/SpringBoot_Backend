package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.supervisor.SupervisorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.springboot.connectmate.services.SupervisorService;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/supervisors")
public class SupervisorController {

    private SupervisorService supervisorService;

    //No need for @Autowired
    public SupervisorController(SupervisorService supervisorService) {
        this.supervisorService = supervisorService;
    }

    // Create Supervisor Rest API
    @PostMapping
    public ResponseEntity<SupervisorDTO> createSupervisor(@RequestBody SupervisorDTO supervisorDTO) {
        return new ResponseEntity<>(supervisorService.createSupervisor(supervisorDTO), HttpStatus.CREATED);
    }

    // Get all Supervisors Rest API
    @GetMapping
    public List<SupervisorDTO> getAllSupervisors() {
        return supervisorService.getAllSupervisors();
    }

}
