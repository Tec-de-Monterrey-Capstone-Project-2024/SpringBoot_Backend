package com.springboot.connectmate.controllers;

import com.springboot.connectmate.services.AmazonConnectInterpreterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/amazon-connect")
@Tag(
        name = "Amazon Connect Interpreter REST API",
        description = "CRUD REST API for Amazon Connect Interpreter"
)
public class AmazonConnectInterpreterController {

    private final AmazonConnectInterpreterService amazonConnectInterpreterService;

    @Autowired
    public AmazonConnectInterpreterController(AmazonConnectInterpreterService amazonConnectInterpreterService) {
        this.amazonConnectInterpreterService = amazonConnectInterpreterService;
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Map.class))),
            description = "Get all the users and contact info for all the queues."
    )
    @Operation(
            summary = "Gets the user and contact data of all queues.",
            description = "Gets the users and contacts of all queues."
    )
    @GetMapping("/instances/{instanceId}/queue-users")
    public ResponseEntity<Map<String, Map<String, Object>>> queueUserCounts(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectInterpreterService.getQueueUserCounts(instanceId));
    }
}