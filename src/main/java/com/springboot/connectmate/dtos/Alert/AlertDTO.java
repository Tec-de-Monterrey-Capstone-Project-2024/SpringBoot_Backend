package com.springboot.connectmate.dtos.Alert;
import com.springboot.connectmate.dtos.Alert.AlertDTO;
import java.time.LocalDateTime;

public class AlertDTO {
    private Long id;
    private Long metricID;
    private String name;
    private String description;
    private String type;
    private String status;
    private String severity;
    private Long minThreshold;
    private Long maxThreshold;
    private String supervisor;
    private String agent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor vacío
    public AlertDTO() {
    }

    // Constructor que acepta un objeto Alert
    public AlertDTO(AlertDTO alert) {
        if (alert != null) {
            this.id = alert.getId();
            this.metricID = alert.getMetricID();
            this.name = alert.getName();
            this.description = alert.getDescription();
            this.severity = alert.getSeverity();           
            this.type = alert.getType();
            this.status = alert.getStatus();
            this.severity = alert.getSeverity();            
            this.minThreshold = alert.getMinThreshold();
            this.maxThreshold = alert.getMaxThreshold();
            this.supervisor = alert.getSupervisor();
            this.agent = alert.getAgent();
            this.createdAt = alert.getCreatedAt();
            this.updatedAt = alert.getUpdatedAt();
        }
    }

    // Método para convertir DTO a entidad Alert
    public AlertDTO mapToAlert() {
        
        AlertDTO alert = new AlertDTO();
        alert.setId(this.id);
        alert.setMetricID(this.metricID);
        alert.setName(this.name);
        alert.setDescription(this.description);
        alert.setType(this.type);
        alert.setStatus(this.status);
        alert.setSeverity(String.valueOf(this.severity)); // Asumiendo que Severity es un Enum en Alert
        alert.setMinThreshold(this.minThreshold);
        alert.setMaxThreshold(this.maxThreshold);
        alert.setSupervisor(this.supervisor);
        alert.setAgent(this.agent);
        alert.setCreatedAt(this.createdAt);
        alert.setUpdatedAt(this.updatedAt);
        return alert;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMetricID() {
        return metricID;
    }

    public void setMetricID(Long metricID) {
        this.metricID = metricID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Long getMinThreshold() {
        return minThreshold;
    }

    public void setMinThreshold(Long minThreshold) {
        this.minThreshold = minThreshold;
    }

    public Long getMaxThreshold() {
        return maxThreshold;
    }

    public void setMaxThreshold(Long maxThreshold) {
        this.maxThreshold = maxThreshold;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}


/*
id (Long): Identificador único de la alerta.
metricID (Long): Identificador de la métrica asociada.
name (String): Nombre de la alerta.
description (String): Descripción de la alerta.
type (String): Tipo de alerta (por ejemplo, "Service Level").
status (String): Estado actual de la alerta (por ejemplo, "Open").
severity (Enum): Severidad de la alerta (LOW, MEDIUM, HIGH).
minThreshold (Long): Umbral mínimo para la alerta.
maxThreshold (Long): Umbral máximo para la alerta.
supervisor (String): Supervisor asociado a la alerta.
agent (String): Agente asociado a la alerta.
createdAt (LocalDateTime): Fecha y hora de creación de la alerta.
updatedAt (LocalDateTime): Fecha y hora de la última actualización de la alerta.
 */