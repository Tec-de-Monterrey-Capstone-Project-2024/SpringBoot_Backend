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
                    description = "Get the insights successfully",
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

        response.add(insight1);
        return ResponseEntity.ok(response);
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