package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.InsightDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/insight")
@Tag(
        name = "Insight REST API",
        description = "An API that have the CRUD services for insights in the Call Center"
)
public class InsightController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Insights fetched successfully.",
                    content = {
                        @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InsightDTO.class))
            }),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content
            )
    })
    @Operation(summary = "Get all insights for the Call Center")
    @GetMapping
    public ResponseEntity<List<InsightDTO>> getAllInsights(){
        List<InsightDTO> response = new ArrayList<>();
        InsightDTO insight1 = new InsightDTO();
        insight1.setId(1L);
        insight1.setType(InsightDTO.InsightType.QUEUE);
        insight1.setStatus(InsightDTO.InsightStatus.TODO);
        insight1.setDescription("Not enough people on virtual floor.");
        insight1.setCreatedAt(LocalDateTime.now());

        InsightDTO insight2 = new InsightDTO();
        insight2.setId(2L);
        insight2.setType(InsightDTO.InsightType.QUEUE);
        insight2.setStatus(InsightDTO.InsightStatus.DONE);
        insight2.setDescription("Add agents to Queue 2.");
        insight2.setCreatedAt(LocalDateTime.now());

        InsightDTO insight3 = new InsightDTO();
        insight3.setId(3L);
        insight3.setType(InsightDTO.InsightType.QUEUE);
        insight3.setStatus(InsightDTO.InsightStatus.TODO);
        insight3.setDescription("Review agents on Queue 2.");
        insight3.setCreatedAt(LocalDateTime.now());

        InsightDTO insight4 = new InsightDTO();
        insight4.setId(3L);
        insight4.setType(InsightDTO.InsightType.QUEUE);
        insight4.setStatus(InsightDTO.InsightStatus.TODO);
        insight4.setDescription("Review agents on Queue 2.");
        insight4.setCreatedAt(LocalDateTime.now());

        response.add(insight1);
        response.add(insight2);
        response.add(insight3);
        response.add(insight4);
        return ResponseEntity.ok(response);
    }

    // Get Insight by ID API
    @Operation(
            summary = "Get Insight by ID",
            description = "Gets a specific insight by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Insight fetched successfully"
    )
    @GetMapping("/{insightId}")
    public InsightDTO getInsightByID(@PathVariable Long insightId){
        InsightDTO insight = new InsightDTO();
        
        insight.setId(1L);
        insight.setType(InsightDTO.InsightType.QUEUE);
        insight.setStatus(InsightDTO.InsightStatus.TODO);
        insight.setDescription("Not enough people on virtual floor.");
        insight.setCreatedAt(LocalDateTime.parse("2007-12-03T10:15:30"));
        insight.setUpdatedAt(LocalDateTime.parse("2007-12-03T10:15:31"));
        
        return insight;
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                description = "Insight updated successfully",
                content = {
                    @Content(
                            schema = @Schema(implementation = InsightDTO.class))
            }),
            @ApiResponse(responseCode = "500",
                description = "Internal Server Error",
                content = @Content
            )
    })
    @Operation(summary = "Modify one specific insight by its identifier (ID)")
    @PutMapping("/{insightId}")
    public ResponseEntity<InsightDTO> updateInsight(@PathVariable Long insightId, @RequestBody InsightDTO insight){
        insight.setId(insightId);
        insight.setUpdatedAt(LocalDateTime.now());

        return ResponseEntity.ok(insight);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "No content"
            ),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content
            )
    })
    @Operation(summary = "Delete one insight by Id in the database")
    @DeleteMapping("/{insightId}")
    public ResponseEntity<String> deleteInsight(@PathVariable Long insightId){
        return ResponseEntity.noContent().build();
    }

}
