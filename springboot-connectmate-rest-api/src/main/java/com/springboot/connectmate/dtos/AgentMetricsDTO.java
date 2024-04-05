package com.springboot.connectmate.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
public class AgentMetricsDTO {
    private double serviceLevel;
    private double occupancy;
    private double scheduleAdherence;
    private double abandonedCalls;
    private double firstCallResolution;
    private double averageAnswerSpeed;

    // Constructor, getters y setters
}
