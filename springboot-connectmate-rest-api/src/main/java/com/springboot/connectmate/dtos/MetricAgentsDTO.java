package com.example.demo.dto;
import lombok.Data;

@Data
public class MetricAgentsDTO{
    @Schema(
            description = "Id of the Supervisor",
            example = "1"
    )
    private int id;
    
    @Schema(
            description = "Name of the KPI",
            example = "Service level"
    )
    private String name;
    
    @Schema(
            description = "KPI's description",
            example = "Calls ansered within"
    )
    private String description;
    
    @Schema(
            description = "KPI's metrics",
            example = "20"
    )
    private int limitInSeconds;
    
    @Schema(
            description = "KPI's metrics",
            example = "80"
    )
    private int answeredCalls;
    
    @Schema(
            description = "KPI's metrics",
            example = "90"
    )
    private int totalCalls;

}
