package com.springboot.connectmate.controllers;


import com.springboot.connectmate.dtos.ThresholdBreachInsight.InsightDTO;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.KpiDataDTO;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachInsightDetailDTO;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachInsightGenericDTO;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.enums.*;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import com.springboot.connectmate.services.BedrockService;
import com.springboot.connectmate.services.ThresholdBreachInsightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
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

    private final ThresholdBreachInsightService thresholdBreachInsightService;
    private final BedrockService bedrockService;

    @Autowired
    public ThresholdBreachInsightController(ThresholdBreachInsightService thresholdBreachInsightService, BedrockService bedrockService) {
        this.thresholdBreachInsightService = thresholdBreachInsightService;
        this.bedrockService = bedrockService;
    }
    @Operation(
            summary = "Creates a ThresholdBreachInsight record",
            description = "Create the thresholdbreachinsight record "
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


        ThresholdBreachInsightDetailDTO dto = new ThresholdBreachInsightDetailDTO();
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

    @Operation(summary = "Get insights by status, connectItemId, or itemType", description = "Retrieve insights based on the provided parameters. If no parameters are provided, all insights are returned.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid parameter"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<ThresholdBreachInsightGenericDTO>> getInsights(
            @RequestParam(name = "status", required = false) Status status,
            @RequestParam(name = "connectItemId", required = false) String connectItemId,
            @RequestParam(name = "itemType", required = false) ConnectMetricType itemType) {


        if (status != null)
            return ResponseEntity.ok(thresholdBreachInsightService.getInsightsByStatus(status));
        else if (connectItemId != null)
            return ResponseEntity.ok(thresholdBreachInsightService.getInsightsByConnectItemId(connectItemId));
        else if (itemType != null)
            return ResponseEntity.ok(thresholdBreachInsightService.getInsightsByItemType(itemType));
        else
            return ResponseEntity.ok(thresholdBreachInsightService.getAllInsights());

    }


    @Operation(summary = "Update the status of an insight", description = "Update the status of a ThresholdBreachInsight by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status updated successfully"),
            @ApiResponse(responseCode = "304", description = "Status not modified"),
            @ApiResponse(responseCode = "400", description = "Invalid status value"),
            @ApiResponse(responseCode = "404", description = "Insight not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/{thresholdId}/status")
    public ResponseEntity<String> updateInsightStatus(
            @PathVariable(name = "thresholdId") Long thresholdId,
            @RequestParam(name = "newStatus") Status newStatus) {
        // Validate the new status
        if (newStatus == null)
            return ResponseEntity.badRequest().body("Invalid status value");

        Status approvedStatus;
        try{
            approvedStatus  = Status.valueOf(newStatus.toString().toUpperCase());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid status value: " + newStatus);
        }

        // Update the status
        String updatedInsight = thresholdBreachInsightService.updateStatus(thresholdId, approvedStatus);
        return ResponseEntity.ok(updatedInsight);
    }
}
