package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.Insight.InsightDTO;
import com.springboot.connectmate.dtos.Insight.InsightStatusUpdateDTO;
import com.springboot.connectmate.dtos.OldDTOS.OldInsightDTO;
import com.springboot.connectmate.enums.InsightStatus;
import com.springboot.connectmate.services.InsightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/insights")
@Tag(
        name = "Insight REST API",
        description = "CRUD REST API for Insights"
)
public class InsightController {

    private final InsightService insightService;

    @Autowired
    public InsightController(InsightService insightService) {
        this.insightService = insightService;
    }

    @ApiResponse(responseCode = "200",
            description = "Insights fetched successfully",
            content = {
                @Content(mediaType = "application/json",
                         schema = @Schema(implementation = OldInsightDTO.class))
    })
    @Operation(summary = "Get all insights for the Call Center")
    @GetMapping
    public ResponseEntity<List<OldInsightDTO>> getAllInsights(){
        List<OldInsightDTO> response = new ArrayList<>();
        OldInsightDTO insight1 = new OldInsightDTO();
        insight1.setId(1L);
        insight1.setType(OldInsightDTO.InsightType.QUEUE);
        insight1.setStatus(OldInsightDTO.InsightStatus.TODO);
        insight1.setDescription("Not enough people on virtual floor.");
        insight1.setCreatedAt(LocalDateTime.now());

        OldInsightDTO insight2 = new OldInsightDTO();
        insight2.setId(2L);
        insight2.setType(OldInsightDTO.InsightType.QUEUE);
        insight2.setStatus(OldInsightDTO.InsightStatus.DONE);
        insight2.setDescription("Add agents to Queue 2.");
        insight2.setCreatedAt(LocalDateTime.now());

        OldInsightDTO insight3 = new OldInsightDTO();
        insight3.setId(3L);
        insight3.setType(OldInsightDTO.InsightType.QUEUE);
        insight3.setStatus(OldInsightDTO.InsightStatus.TODO);
        insight3.setDescription("Review agents on Queue 2.");
        insight3.setCreatedAt(LocalDateTime.now());

        OldInsightDTO insight4 = new OldInsightDTO();
        insight4.setId(4L);
        insight4.setType(OldInsightDTO.InsightType.OTHER);
        insight4.setStatus(OldInsightDTO.InsightStatus.TODO);
        insight4.setDescription("Review clients on Queue 1.");
        insight4.setCreatedAt(LocalDateTime.now());

        response.add(insight1);
        response.add(insight2);
        response.add(insight3);
        response.add(insight4);
        return ResponseEntity.ok(response);
    }

    // Get Insight by ID API
    @ApiResponse(
            responseCode = "200",
            description = "Insight fetched successfully"
    )
    @Operation(
            summary = "Get Insight by ID",
            description = "Gets a specific insight by its ID."
    )
    @GetMapping("/{insightId}")
    public OldInsightDTO getInsightByID(@PathVariable Long insightId){
        OldInsightDTO insight = new OldInsightDTO();
        
        insight.setId(insightId);
        insight.setType(OldInsightDTO.InsightType.QUEUE);
        insight.setStatus(OldInsightDTO.InsightStatus.TODO);
        insight.setDescription("Not enough people on virtual floor.");
        insight.setCreatedAt(LocalDateTime.parse("2007-12-03T10:15:30"));
        insight.setUpdatedAt(LocalDateTime.parse("2007-12-03T10:15:31"));
        
        return insight;
    }

    // Get Insight by Breach ID API
    @ApiResponse(
            responseCode = "200",
            description = "Insight fetched successfully"
    )
    @Operation(
            summary = "Get Insight by ID",
            description = "Gets a specific insight by its ID."
    )
    @GetMapping("breach/{insightId}")
    public InsightDTO getInsightByBreachID(@PathVariable Long insightId){
        return insightService.getInsightByBreachId(insightId);
    }

    // Get Queue Insights API
    @ApiResponse(
            responseCode = "200",
            description = "Insights fetched successfully"
    )
    @Operation(
            summary = "Get Insights by QueueID",
            description = "Gets all Queue insights"
    )
    @GetMapping("/queues")
    public List<InsightDTO> getQueueInsights(){
        return insightService.getQueueInsights();
    }

    @ApiResponse(responseCode = "200",
            description = "Insight updated successfully",
            content = {
                @Content(
                        schema = @Schema(implementation = OldInsightDTO.class))
    })
    @Operation(summary = "Modify one specific insight by its identifier (ID)")
    @PutMapping("/{insightId}")
    public ResponseEntity<OldInsightDTO> updateInsight(@PathVariable Long insightId, @RequestBody OldInsightDTO insight){
        insight.setId(insightId);
        insight.setUpdatedAt(LocalDateTime.now());

        return ResponseEntity.ok(insight);
    }

    @ApiResponse(responseCode = "204",
            description = "No content"
    )
    @Operation(summary = "Delete one insight by Id in the database")
    @DeleteMapping("/{insightId}")
    public ResponseEntity<String> deleteInsight(@PathVariable Long insightId){
        return ResponseEntity.noContent().build();
    }

    @Operation(
      summary = "Update Insight Status"
      description = "Updates the Insight Status With a New Status"
    )
    @ApiResponse(
      responseCode = "200", 
      description = "Status updated successfully")
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateInsightStatus(@PathVariable Long id, @RequestBody InsightStatusUpdateDTO statusUpdateDTO) {
        insightService.updateInsightStatus(id, statusUpdateDTO.getStatus());
        return ResponseEntity.ok().build();
    }

}
