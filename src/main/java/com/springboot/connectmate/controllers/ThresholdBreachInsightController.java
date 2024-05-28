package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.Insight.ThresholdBreachInsightDTO;
import com.springboot.connectmate.dtos.Insight.UpdateStatusDTO;
import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.services.ThresholdBreachInsightService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/threshold-breach-insights")
@Tag(
        name = "Connectmate Threshold Breach Insights REST API",
        description = "CRUD REST API for Connectmate Threshold Breach Insights"
)
public class ThresholdBreachInsightController {

    private final ThresholdBreachInsightService service;

    @Autowired
    public ThresholdBreachInsightController(ThresholdBreachInsightService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ThresholdBreachInsightDTO>> getInsights(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String connectItemId,
            @RequestParam(required = false) String itemType) {

        List<ThresholdBreachInsightDTO> insights;

        if (status != null) {
            Status statusEnum = Status.fromString(status.toUpperCase());
            insights = service.getInsightsByStatus(statusEnum);
        } else if (connectItemId != null) {
            insights = service.getInsightsByConnectItemId(connectItemId);
        } else if (itemType != null) {
            ConnectMetricType connectItemType = ConnectMetricType.valueOf(itemType.toUpperCase());
            insights = service.getInsightsByItemType(connectItemType);
        } else {
            insights = service.getAllInsights();
        }

        return ResponseEntity.ok(insights);
    }


    @PatchMapping("/{id}/status")
    public ResponseEntity<ThresholdBreachInsightDTO> updateInsightStatus(@PathVariable Long id, @RequestBody UpdateStatusDTO updateStatusDTO) {
        ThresholdBreachInsightDTO updatedInsight = service.updateStatus(id, updateStatusDTO);
        return ResponseEntity.ok(updatedInsight);
    }


}
