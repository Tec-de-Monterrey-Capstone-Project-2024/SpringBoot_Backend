package com.springboot.connectmate.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    private final ThresholdBreachInsightService thresholdBreachInsightService;

    @Autowired
    public ThresholdBreachInsightController(ThresholdBreachInsightService thresholdBreachInsightService) {
        this.thresholdBreachInsightService = thresholdBreachInsightService;
    }


    @Operation(summary = "Get insights by status, connectItemId, or itemType", description = "Retrieve insights based on the provided parameters. If no parameters are provided, all insights are returned.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid parameter"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<ThresholdBreachInsightDTO>> getInsights(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String connectItemId,
            @RequestParam(required = false) String itemType) {


        if (status != null) {
            Status statusEnum = Status.fromString(status.toUpperCase());
            return ResponseEntity.ok(thresholdBreachInsightService.getInsightsByStatus(statusEnum));
        } else if (connectItemId != null) {
            return ResponseEntity.ok(thresholdBreachInsightService.getInsightsByConnectItemId(connectItemId));
        } else if (itemType != null) {
            ConnectMetricType connectItemType = ConnectMetricType.valueOf(itemType.toUpperCase());
            return ResponseEntity.ok(thresholdBreachInsightService.getInsightsByItemType(connectItemType));
        } else {
            return ResponseEntity.ok(thresholdBreachInsightService.getAllInsights());
        }

    }


    @Operation(summary = "Update the status of an insight", description = "Update the status of a ThresholdBreachInsight by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status updated successfully"),
            @ApiResponse(responseCode = "404", description = "Insight not found"),
            @ApiResponse(responseCode = "400", description = "Invalid status value"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateInsightStatus(@PathVariable Long id, @RequestBody UpdateStatusDTO updateStatusDTO) {
        String updatedInsight = thresholdBreachInsightService.updateStatus(id, updateStatusDTO);
        return ResponseEntity.ok(updatedInsight);
    }


}
