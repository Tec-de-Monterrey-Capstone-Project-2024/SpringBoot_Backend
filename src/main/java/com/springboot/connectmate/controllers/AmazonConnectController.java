package com.springboot.connectmate.controllers;

import com.amazonaws.services.connect.model.*;
import com.springboot.connectmate.services.AmazonConnectService;
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
        name = "Amazon Connect REST API",
        description = "CRUD REST API for Amazon Connect"
)
public class AmazonConnectController {

    private final AmazonConnectService amazonConnectService;

    @Autowired
    public AmazonConnectController(AmazonConnectService amazonConnectService) {
        this.amazonConnectService = amazonConnectService;
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = InstanceSummary.class))),
            description = "List of instances fetched successfully."
    )
    @Operation(
            summary = "Get instances",
            description = "Get instances for an specific Amazon region with a given AWS account"
    )
    @GetMapping("/instances")
    public ResponseEntity<List<InstanceSummary>> listConnectInstances() {
        return ResponseEntity.ok(amazonConnectService.listConnectInstances());
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Instance.class))),
            description = "Instance fetched successfully."
    )
    @Operation(
            summary = "Get Instance By Id",
            description = "Get instance details by Id for an specific Amazon region with a given AWS account"
    )
    @GetMapping("/instances/{instanceId}")
    public ResponseEntity<Instance> getConnectInstance(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.getConnectInstance(instanceId));
    }

