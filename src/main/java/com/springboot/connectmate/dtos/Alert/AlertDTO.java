package com.springboot.connectmate.dtos.Alert;

import com.springboot.connectmate.dtos.Metric.MetricDescriptionDTO;
import com.springboot.connectmate.enums.ThresholdBreachPerformanceCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(
        name = "Alert",
        description = "DTO for Alert"
)
public class AlertDTO {

    @Schema(
            description = "Id of the Alert",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Metric Description associated with this alert. " +
                    "Contains details such as metric ID, code, and " +
                    "description related to the specific metric causing " +
                    "the alert."
    )
    private MetricDescriptionDTO metric;

    @Schema(
            description = "Value of the Alert",
            example = "70"
    )
    private double value;

    @Schema(
            description = "Breach Category of the Alert",
            example = "CRITICAL"
    )
    private ThresholdBreachPerformanceCategory breachCategory;

    @Schema(
            description = "Occurred At of the Alert",
            example = "2021-07-01T00:00:00"
    )
    private LocalDateTime occurredAt;

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