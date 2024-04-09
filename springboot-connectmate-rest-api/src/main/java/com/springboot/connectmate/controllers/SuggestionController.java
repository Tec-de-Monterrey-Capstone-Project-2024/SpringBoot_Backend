package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.SuggestionDTO;
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
@RequestMapping("/api/suggestion")
@Tag(
        name = "Suggestion REST API",
        description = "An API that have the CRUD services for suggestions in the Call Center"
)
public class SuggestionController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Get the suggestions successfully",
                    content = {
                        @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SuggestionDTO.class))
            }),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content
            )
    })
    @Operation(summary = "Get all suggestions for the Call Center")
    @GetMapping
    public ResponseEntity<List<SuggestionDTO>> getSuggestions(){
        List<SuggestionDTO> response = new ArrayList<>();
        SuggestionDTO suggestion1 = new SuggestionDTO();
        suggestion1.setId(1L);
        suggestion1.setType(SuggestionDTO.SuggestionTopic.QUEUE);
        suggestion1.setStatus(SuggestionDTO.SuggestionStatus.TODO);
        suggestion1.setDescription("Not enough people on virtual floor.");
        suggestion1.setDetails("http://localhost:8080/api/suggestion/1");
        suggestion1.setCreatedAt(LocalDateTime.now());

        response.add(suggestion1);
        return ResponseEntity.ok(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                description = "Suggestion updated successfully",
                content = {
                    @Content(
                            schema = @Schema(implementation = SuggestionDTO.class))
            }),
            @ApiResponse(responseCode = "500",
                description = "Internal Server Error",
                content = @Content
            )
    })
    @Operation(summary = "Modify one specific suggestion by its identifier (ID)")
    @PutMapping("/{taskId}")
    public ResponseEntity<SuggestionDTO> updateSuggestion(@PathVariable Long taskId, @RequestBody SuggestionDTO suggestion){
        suggestion.setId(taskId);
        return ResponseEntity.ok(suggestion);
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
    @Operation(summary = "Delete one suggestion by Id in the database")
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteSuggestion(@PathVariable Long taskId){
        return ResponseEntity.noContent().build();
    }

}
