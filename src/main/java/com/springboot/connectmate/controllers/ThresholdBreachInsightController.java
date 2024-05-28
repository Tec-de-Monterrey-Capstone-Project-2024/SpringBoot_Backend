package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.Update.UpdateStatusDTO;
import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import com.springboot.connectmate.services.ThresholdBreachInsightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ThresholdBreachInsight.class)),
            description = "Status updated successfully"
    )
    @Operation(
            summary = "Update status",
            description = "Update the status of a Threshold Breach Insight by ID."
    )
    @PatchMapping("/{id}/status")
    public ThresholdBreachInsight updateStatus(@PathVariable Long id, @RequestBody UpdateStatusDTO updateStatusDTO) {
        return thresholdBreachInsightService.updateStatus(id, updateStatusDTO.getStatus());
    }
}
