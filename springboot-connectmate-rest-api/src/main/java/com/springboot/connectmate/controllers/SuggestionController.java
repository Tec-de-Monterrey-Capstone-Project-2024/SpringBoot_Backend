package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.SuggestionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @ApiResponse(responseCode = "200")

    })
    @Operation(summary = "Get all suggestions for the Call Center")
    @GetMapping
    public ResponseEntity<List<SuggestionDTO>> getSuggestions(){
        List<SuggestionDTO> response = new ArrayList<>();
        response.add(new SuggestionDTO());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuggestionDTO> updateSuggestion(@PathVariable Long id){
        SuggestionDTO suggestion = new SuggestionDTO();
        suggestion.setId(id);
        return ResponseEntity.ok(suggestion);
    }

}
