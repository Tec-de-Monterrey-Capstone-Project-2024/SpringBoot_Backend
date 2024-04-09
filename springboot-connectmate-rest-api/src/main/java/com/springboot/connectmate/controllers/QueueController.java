package com.springboot.connectmate.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/queues")
@Tag(
        name = "Queue REST API",
        description = "CRUD REST API for Queues"
)
public class QueueController {
    @Operation(
            summary = "Get all queues",
            description = "Get all queues"
    )
    @ApiResponse (
            responseCode = "200",
            description = "Queues fetched successfully"
    )
    @GetMapping
    public ResponseEntity<List<QueueDTO>> getQueue(){
        List<QueueDTO> result = new ArrayList<>();
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Get Queue by ID",
            description = "Get Queue by ID"
    )
    @ApiResponse (
            responseCode = "200",
            description = "Queue fetched successfully"
    )
    @GetMapping("/{id}")
    public ResponseEntity<QueueDTO>getQueueById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(new QueueDTO());
    }
}
