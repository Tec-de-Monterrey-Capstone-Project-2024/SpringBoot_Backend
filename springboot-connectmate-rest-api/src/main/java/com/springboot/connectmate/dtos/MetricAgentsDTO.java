package com.example.demo.dto;
import lombok.Data;

@Data
public class MetricAgentsDTO{
    private int id;
    private String name;
    private String description;
    private int limitInSeconds;
    private int answeredCalls;
    private int totalCalls;

}
