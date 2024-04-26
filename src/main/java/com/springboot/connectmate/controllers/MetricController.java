package com.springboot.connectmate.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metrics")
@Tag(
        name = "Metric REST API",
        description = "CRUD REST API for Metrics"
)
public class MetricController {
}
