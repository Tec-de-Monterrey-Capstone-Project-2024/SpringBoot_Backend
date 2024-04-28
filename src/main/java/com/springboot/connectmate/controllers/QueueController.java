package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.Insight.InsightDTO;
import com.springboot.connectmate.dtos.OldDTOS.OldQueueDTO;
import com.springboot.connectmate.dtos.User.UserDTO;
import com.springboot.connectmate.services.QueueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping("/api/queues")
@Tag(
        name = "Queue REST API",
        description = "CRUD REST API for Queues"
)
public class QueueController {

    private final QueueService queueService;

    @Autowired
    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @Operation(
            summary = "Get all queues",
            description = "Get all queues"
    )
    @ApiResponse (
            responseCode = "200",
            description = "Queues fetched successfully"
    )
    @GetMapping
    public ResponseEntity<List<OldQueueDTO>> getQueues(){
        List<OldQueueDTO> result = new ArrayList<>();
        OldQueueDTO queue1 = new OldQueueDTO();
        queue1.setId(1L);
        queue1.setClients(19L);
        queue1.setAgents(32L);
        queue1.setFullstatus(OldQueueDTO.status.FULL);
        OldQueueDTO queue2 = new OldQueueDTO();
        queue2.setId(2L);
        queue2.setAgents(20L);
        queue2.setClients(31L);
        queue2.setFullstatus(OldQueueDTO.status.MEDIUM);
        OldQueueDTO queue3 = new OldQueueDTO();
        queue3.setId(3L);
        queue3.setAgents(2L);
        queue3.setClients(10L);
        queue3.setFullstatus(OldQueueDTO.status.LOW);
        result.add(queue1);
        result.add(queue2);
        result.add(queue3);
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
    public ResponseEntity<OldQueueDTO>getQueueById(@PathVariable(name = "id") long id){
        OldQueueDTO queue1 = new OldQueueDTO();
        queue1.setId(1L);
        queue1.setClients(19L);
        queue1.setAgents(32L);
        queue1.setFullstatus(OldQueueDTO.status.FULL);
        return ResponseEntity.ok(queue1);
    }

    // Get all agents from a particular queue.
    @Operation(
            summary = "Get all queue agents",
            description = "Get all agents by their queue ID."
    )
    @ApiResponse (
            responseCode = "200",
            description = "Agents fetched successfully"
    )
    @GetMapping("/{id}/agents")
    public List<UserDTO> getQueueAgents(@PathVariable Long id){
        return queueService.getQueueAgents(id);
    }
}
