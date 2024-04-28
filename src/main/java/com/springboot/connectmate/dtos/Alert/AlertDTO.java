package com.springboot.connectmate.dtos.Alert;

import lombok.Data;
import java.time.LocalDateTime;

@Data
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