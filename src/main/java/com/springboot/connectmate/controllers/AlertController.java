package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.Alert.AlertDTO;
import com.springboot.connectmate.dtos.OldDTOS.OldAlertDTO.severity;
import com.springboot.connectmate.services.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AlertController {

    public static severity severity;
private final AlertService alertService;

    @Autowired
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    // Endpoint to get all threshold breaches
    @GetMapping("/threshold-breaches")
    public ResponseEntity<List<AlertDTO>> getAllThresholdBreaches() {
        List<AlertDTO> alerts = alertService.getAllAlerts();
        return ResponseEntity.ok(alerts);
    }
}
