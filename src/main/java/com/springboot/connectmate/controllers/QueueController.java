package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.User.UserDTO;
import com.springboot.connectmate.dtos.Queue.QueueDTO;
import com.springboot.connectmate.services.QueueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<QueueDTO> getQueues(){
        return queueService.getQueues();
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
    @GetMapping("/{queueId}/agents")
    public List<UserDTO> getQueueAgents(@PathVariable Long queueId){
        return queueService.getQueueAgents(queueId);
    }
}
