package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.AmazonConnect.*;
import com.springboot.connectmate.services.AmazonConnectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/instances")
    public ResponseEntity<List<ConnectInstanceDTO>> listConnectInstances() {
        return ResponseEntity.ok(amazonConnectService.listConnectInstances());
    }

    @GetMapping("/instances/{instanceId}/queues")
    public ResponseEntity<List<ConnectQueueDTO>> listQueues(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.listQueues(instanceId));
    }

    @GetMapping("/instances/{instanceId}/users")
    public ResponseEntity<List<ConnectUserDTO>> listUsers(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.listUsers(instanceId));
    }

    @GetMapping("/instances/{instanceId}/agents")
    public ResponseEntity<List<ConnectAgentDTO>> listAgents(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.listAgents(instanceId));
    }

    @GetMapping("/instances/{instanceId}/historial-metrics")
    public ResponseEntity<List<String>> getHistoricalMetrics(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.getHistoricalMetrics(instanceId));
    }

    @GetMapping("/instances/{instanceId}/routing-profiles")
    public ResponseEntity<List<ConnectRoutingProfileDTO>> getRoutingProfiles(@PathVariable(name = "instanceId") String instanceId) {
        return ResponseEntity.ok(amazonConnectService.listRoutingProfiles(instanceId));
    }

}
