package com.springboot.connectmate.controllers;

import com.amazonaws.services.connect.model.*;
import com.amazonaws.services.connect.model.Queue;
import com.springboot.connectmate.services.AmazonConnectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/amazon-connect-users")
@Tag(
        name = "Amazon Connect Users REST API",
        description = "CRUD REST API for Amazon Connect User"
)
public class ConnectUserController {

    private final AmazonConnectService amazonConnectService;

    @Autowired
    public ConnectUserController(AmazonConnectService amazonConnectService) {
        this.amazonConnectService = amazonConnectService;
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = UserSummary.class))),
            description = "List of users for a given instance fetched successfully."
    )
    @Operation(
            summary = "Get all users",
            description = "Get Amazon Connect users (supervisors, agents, etc) by instance ID"
    )
    @GetMapping("/instances/{instanceId}/users")
    public ResponseEntity<List<UserSummary>> listUsers(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.listUsers(instanceId));
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = User.class))),
            description = "Get a user's info."
    )
    @Operation(
            summary = "Gets the data of a particular user.",
            description = "Gets the data of a particular user by instance ID and user ID."
    )
    @GetMapping("/instances/{instanceId}/users/{userId}/description")
    public ResponseEntity<User> getUserDescription(@PathVariable(name = "instanceId") String instanceId, @PathVariable(name = "userId") String userId) {
        return ResponseEntity.ok(amazonConnectService.getUserDescription(instanceId, userId));
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AgentStatusSummary.class))),
            description = "List of agent statuses for a given instance fetched successfully."
    )
    @Operation(
            summary = "Get all agent statuses",
            description = "Get Amazon Connect agent statuses (routable, custom, offline) by instance ID"
    )
    @GetMapping("/instances/{instanceId}/agent-statuses")
    public ResponseEntity<List<AgentStatusSummary>> listAgents(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.listAgentsStatuses(instanceId));
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = RoutingProfileSummary.class))),
            description = "List of routing profiles for a given instance fetched successfully."
    )
    @Operation(
            summary = "Get all routing profiles",
            description = "Get Amazon Connect routing profiles by instance ID"
    )
    @GetMapping("/instances/{instanceId}/routing-profiles")
    public ResponseEntity<List<RoutingProfileSummary>> getRoutingProfiles(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.listRoutingProfiles(instanceId));
    }

}
