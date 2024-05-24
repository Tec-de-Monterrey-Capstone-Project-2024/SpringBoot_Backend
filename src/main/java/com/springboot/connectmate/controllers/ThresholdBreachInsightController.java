package com.springboot.connectmate.controllers;

import com.springboot.connectmate.services.ThresholdBreachInsightService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/threshold-breach-insights")
@Tag(
        name = "Connectmate Threshold Breach Insights REST API",
        description = "CRUD REST API for Connectmate Threshold Breach Insights"
)
public class ThresholdBreachInsightController {

    private final ThresholdBreachInsightService thresholdBreachInsightService;

    @Autowired
    public ThresholdBreachInsightController(ThresholdBreachInsightService thresholdBreachInsightService) {
        this.thresholdBreachInsightService = thresholdBreachInsightService;
    }
}
