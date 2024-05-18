package com.springboot.connectmate.controllers;

import com.springboot.connectmate.services.ThresholdBreachesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Queue;

@RestController
@RequestMapping("/api/threshold-breaches")
@Tag(
        name = "Threshold Breaches REST API",
        description = "CRUD REST API for Threshold Breaches."
)
public class ThresholdBreachesController {

    private final ThresholdBreachesService thresholdBreachesService;

    @Autowired
    public ThresholdBreachesController(ThresholdBreachesService thresholdBreachesService) {
        this.thresholdBreachesService = thresholdBreachesService;
    }
}
