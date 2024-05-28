package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.AmazonConnect.InsightDTO;
import com.springboot.connectmate.dtos.AmazonConnect.InsightStatusUpdateDTO;
import com.springboot.connectmate.dtos.AmazonConnect.KpiDataDTO;
import com.springboot.connectmate.dtos.AmazonConnect.ThresholdBreachInsightDTO;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import com.springboot.connectmate.services.BedrockService;
import com.springboot.connectmate.services.ThresholdBreachInsightService;
import com.springboot.connectmate.services.impl.BedrockServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/threshold-breach-insights")
@Tag(
        name = "Connectmate Threshold Breach Insights REST API",
        description = "CRUD REST API for Connectmate Threshold Breach Insights"
)
public class ThresholdBreachInsightController {

    private final ThresholdBreachInsightService thresholdBreachInsightService;
    private final BedrockService bedrockService;

    @Autowired
    public ThresholdBreachInsightController(ThresholdBreachInsightService thresholdBreachInsightService, BedrockService bedrockService) {
        this.thresholdBreachInsightService = thresholdBreachInsightService;
        this.bedrockService = bedrockService;
    }


    @Operation(
            summary = "Update Insight Status",
            description = "Updates the Insight Status With a New Status"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Status updated successfully"
    )
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateInsightStatus(
            @PathVariable Long id,
            @RequestBody InsightStatusUpdateDTO statusUpdateDTO
    ) {
        Status newStatus = statusUpdateDTO.getNewStatus();
        thresholdBreachInsightService.updateInsightStatus(id, newStatus);
        return ResponseEntity.ok("Status updated successfully");
    }


    /*
    @PostMapping("/generateAndSaveInsight")
    public ResponseEntity<ThresholdBreachInsight> generateAndSaveInsight(
            @RequestBody ThresholdBreachInsightDTO dto,
            @RequestParam Double metricValue,
            @RequestParam ConnectMetricType metricType,
            @RequestParam String typeId) {

        // Call the BedrockService to get the insight
        InsightDTO insight = bedrockService.createInsight(dto);

        // Fill in additional details from the request parameters
        dto.setValue(metricValue);
        dto.setConnectItemType(metricType);
        dto.setConnectItemId(typeId);

        // Generate and save the insight
        ThresholdBreachInsight savedInsight = thresholdBreachInsightService.generateAndSaveInsight(dto, insight);
        return ResponseEntity.ok(savedInsight);
    }*/

}
