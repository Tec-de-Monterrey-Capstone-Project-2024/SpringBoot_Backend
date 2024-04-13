package com.springboot.connectmate.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/agents")
public class MetricsController {

    @GetMapping("/metrics")
    public Map<String, Double> getMetrics() {
        Map<String, Double> metrics = new HashMap<>();

        metrics.put("Service Level", 95.5);
        metrics.put("Occupancy", 80.0);
        metrics.put("Schedule Adherence", 92.3);
        metrics.put("Abandoned Calls", 3.0);
        metrics.put("First Call Resolution", 75.0);
        metrics.put("Average Answer Speed", 20.5);

        return metrics;
    }
}
