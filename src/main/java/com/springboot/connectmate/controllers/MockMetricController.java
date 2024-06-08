package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.MockMetric.MockMetricDTO;
import com.springboot.connectmate.services.MockMetricService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Hidden
@RestController
@RequestMapping("/api/mock-metrics")
@Tag(
        name = "Mock Metrics REST API",
        description = "CRUD REST API for Mock Metrics"
)
public class MockMetricController {

    private final MockMetricService mockMetricService;

    @Autowired
    public MockMetricController(MockMetricService mockMetricService) {
        this.mockMetricService = mockMetricService;
    }

    @GetMapping("/instances")
    public ResponseEntity<List<MockMetricDTO>> getAllInstanceMetrics() {
        List<MockMetricDTO> mockMetrics = mockMetricService.getAllInstanceMetrics();
        return ResponseEntity.ok(mockMetrics);
    }

    @GetMapping("/queues")
    public ResponseEntity<List<MockMetricDTO>> getAllQueueMetrics() {
        List<MockMetricDTO> mockMetrics = mockMetricService.getAllQueueMetrics();
        return ResponseEntity.ok(mockMetrics);
    }

    @GetMapping("/agents")
    public ResponseEntity<List<MockMetricDTO>> getAllAgentMetrics() {
        List<MockMetricDTO> mockMetrics = mockMetricService.getAllAgentMetrics();
        return ResponseEntity.ok(mockMetrics);
    }

}
