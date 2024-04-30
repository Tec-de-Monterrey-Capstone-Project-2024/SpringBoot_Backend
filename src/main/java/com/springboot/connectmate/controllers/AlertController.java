package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.Alert.AlertDTO;
import com.springboot.connectmate.services.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AlertController {

    private final AlertService alertService;

    @Autowired
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    // Endpoint para obtener todas las alertas
    @GetMapping("/threshold-breaches")
    public ResponseEntity<List<AlertDTO>> getAllThresholdBreaches() {
        List<AlertDTO> alerts = alertService.getAllAlerts();
        return ResponseEntity.ok(alerts);
    }

    // Endpoint para obtener una alerta específica por ID
    @GetMapping("/alerts/{id}")
    public ResponseEntity<AlertDTO> getAlertById(@PathVariable Long id) {
        AlertDTO alert = alertService.getAlertById(id);
        return ResponseEntity.ok(alert);
    }

    // Endpoint para crear una nueva alerta
    @PostMapping("/alerts")
    public ResponseEntity<AlertDTO> createAlert(@RequestBody AlertDTO alertDTO) {
        AlertDTO newAlert = alertService.createAlert(alertDTO);
        return ResponseEntity.ok(newAlert);
    }

    // Endpoint para actualizar una alerta existente
    @PutMapping("/alerts/{id}")
    public ResponseEntity<AlertDTO> updateAlert(@PathVariable Long id, @RequestBody AlertDTO alertDTO) {
        alertDTO.setId(id);
        AlertDTO updatedAlert = alertService.updateAlert(alertDTO);
        return ResponseEntity.ok(updatedAlert);
    }

    // Endpoint para eliminar una alerta
    @DeleteMapping("/alerts/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long id) {
        alertService.deleteAlert(id);
        return ResponseEntity.ok().build();
    }
}
