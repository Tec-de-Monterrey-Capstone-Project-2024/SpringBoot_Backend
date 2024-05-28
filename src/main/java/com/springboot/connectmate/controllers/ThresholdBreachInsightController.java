package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.AmazonConnect.InsightDTO;
import com.springboot.connectmate.dtos.AmazonConnect.KpiDataDTO;
import com.springboot.connectmate.dtos.AmazonConnect.ThresholdBreachInsightDTO;
import com.springboot.connectmate.enums.*;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import com.springboot.connectmate.services.BedrockService;
import com.springboot.connectmate.services.ThresholdBreachInsightService;
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
            summary = "Creates a ThresholdBreachInsight record",
            description = "Create the a   "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Insight created successfully"
    )

    @PostMapping("/generateAndSaveInsight")
    public ResponseEntity<String> generateAndSaveInsight(
            @RequestBody KpiDataDTO kpiDataDTO,
            @RequestParam Double metricValue,
            @RequestParam ConnectMetricType metricType,
            @RequestParam String typeId,
            @RequestParam ConnectMetricCode metricCode,
            @RequestParam Status status) {

        InsightDTO insight = bedrockService.createInsight(kpiDataDTO);

        ThresholdBreachInsightDTO dto = new ThresholdBreachInsightDTO();
        dto.setValue(metricValue);
        dto.setConnectItemType(metricType);
        dto.setConnectItemId(typeId);
        dto.setMetricCode(metricCode);
        dto.setStatus(status);

        dto.setInsightName(insight.getInsightName());
        dto.setInsightSummary(insight.getInsightSummary());
        dto.setInsightDescription(insight.getInsightDescription());
        dto.setInsightActions(insight.getInsightActions());
        dto.setInsightSeverity(InsightSeverity.valueOf(insight.getInsightCategory()));
        dto.setInsightCategory(InsightPerformance.valueOf(insight.getInsightPerformance()));
        dto.setInsightRootCause(insight.getInsightRootCause());
        dto.setInsightImpact(insight.getInsightImpact());
        dto.setInsightPrevention(insight.getInsightPrevention());


        ThresholdBreachInsight savedInsight = thresholdBreachInsightService.generateAndSaveInsight(dto, insight);

        return ResponseEntity.ok("Insight created successfully");
    }

}
