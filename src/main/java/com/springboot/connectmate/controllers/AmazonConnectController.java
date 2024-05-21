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
                               array = @ArraySchema(schema = @Schema(implementation = QueueSummary.class))),
            description = "List of queues for a given instance fetched successfully."
    )
    @Operation(
            summary = "Get all queues",
            description = "Get Amazon Connect queues by instance ID"
    )
    @GetMapping("/instances/{instanceId}/queues")
    public ResponseEntity<List<QueueSummary>> listQueues(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.listQueues(instanceId));
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
                               array = @ArraySchema(schema = @Schema(implementation = AgentStatusSummary.class))),
            description = "List of agent statuses for a given instance fetched successfully."
    )
    @Operation(
            summary = "Get all agent statuses",
            description = "Get Amazon Connect agent statuses (routable, custom, offline) by instance ID"
    )
    @GetMapping("/instances/{instanceId}/agent-statuses")
    public ResponseEntity<List<AgentStatusSummary>> listAgents(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.listAgents(instanceId));
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                               array = @ArraySchema(schema = @Schema(implementation = String.class))),
            description = "List of historical metrics for a given instance fetched successfully."
    )
    @Operation(
            summary = "Get all historical metrics",
            description = "Get Amazon Connect historical metrics by instance ID (maximum 24 hours)"
    )
    @GetMapping("/instances/historical-metrics")
    public ResponseEntity<List<String>> getHistoricalMetricsV2(
        @RequestParam(name = "instanceArn") String instanceArn,
        @RequestParam(name = "queueId") String queueId
    ){
        return ResponseEntity.ok(amazonConnectService.getHistoricalMetricsV2(instanceArn, queueId));
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = String.class))),
            description = "List of historical metrics for a given instance fetched successfully"
    )
    @GetMapping("/instances/{instanceId}/queues/{queueId}/historial-metrics")
    public ResponseEntity<List<String>> getHistoricalMetrics(
            @PathVariable(name = "instanceId") String instanceId,
            @PathVariable(name = "queueId") String queueId) {
        return ResponseEntity.ok(amazonConnectService.getHistoricalMetrics(instanceId, queueId));
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = String.class))),
            description = "List of current metrics for a given instance fetched successfully"
    )
    @Operation(
            summary = "Get all current metrics",
            description = "Get Amazon Connect current metrics by instanceARN."
    )
    @GetMapping("/instances/current-metrics")
    public ResponseEntity<List<String>> getCurrentMetrics(
            @RequestParam(name = "instanceArn") String instanceArn){
        return ResponseEntity.ok(amazonConnectService.getCurrentMetrics(instanceArn));
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

    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = UserData.class))),
            description = "List of all current data of agents, queues, and contacts."
    )
    @Operation(
            summary = "Get the current data of agents, queues, and contacts.",
            description = "Get the current data of agents, queues, and contacts by instance ID"
    )
    @GetMapping("/instances/{instanceId}/current-data")
    public ResponseEntity<List<UserData>> getCurrentData(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.getCurrentData(instanceId));
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
                    array = @ArraySchema(schema = @Schema(implementation = Queue.class))),
            description = "Get a queue's info."
    )
    @Operation(
            summary = "Gets the data of a particular queue.",
            description = "Gets the data of a particular queue by instance ID and queue ID."
    )
    @GetMapping("/instances/{instanceId}/queues /{queueId}/description")
    public ResponseEntity<Queue> describeQueue(@PathVariable(name = "instanceId") String instanceId, @PathVariable(name = "queueId") String queueId) {
        return ResponseEntity.ok(amazonConnectService.describeQueue(instanceId, queueId));
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
    public Map<String, Map<String, Object>> queueUserCounts(@PathVariable(name = "instanceId") String instanceId) {
        ResponseEntity<List<UserData>> response = getCurrentData(instanceId);
        List<UserData> userDataList = response.getBody();
        Map<String, Map<String, Object>> queueInfo = new HashMap<>();

        if (userDataList != null) {
            for (UserData userData : userDataList) {
                String userId = userData.getUser().getId();
                for (AgentContactReference contact : userData.getContacts()) {
                    String queueId = contact.getQueue().getId();
                    queueInfo.putIfAbsent(queueId, new HashMap<>());
                    queueInfo.get(queueId).putIfAbsent("users", new HashSet<>());
                    queueInfo.get(queueId).putIfAbsent("contactCount", 0);

                    Set<String> users = (Set<String>) queueInfo.get(queueId).get("users");
                    users.add(userId);

                    int count = (int) queueInfo.get(queueId).get("contactCount");
                    queueInfo.get(queueId).put("contactCount", count + 1);
                }
            }
        }

        return queueInfo;
    }
}
