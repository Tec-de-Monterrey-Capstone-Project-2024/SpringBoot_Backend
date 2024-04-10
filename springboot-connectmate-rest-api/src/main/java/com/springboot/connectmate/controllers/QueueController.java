package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.QueueDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Operation(
            summary = "Get all queues",
            description = "Get all queues"
    )
    @ApiResponse (
            responseCode = "200",
            description = "Queues fetched successfully"
    )
    @GetMapping
    public ResponseEntity<List<QueueDTO>> getQueues(){
        List<QueueDTO> result = new ArrayList<>();
        QueueDTO queue1 = new QueueDTO();
        queue1.setId(1L);
        queue1.setClients(19L);
        queue1.setAgents(32L);
        queue1.setFullstatus(QueueDTO.status.FULL);
        QueueDTO queue2 = new QueueDTO();
        queue2.setId(2L);
        queue2.setAgents(20L);
        queue2.setClients(31L);
        queue2.setFullstatus(QueueDTO.status.MEDIUM);
        QueueDTO queue3 = new QueueDTO();
        queue3.setId(3L);
        queue3.setAgents(2L);
        queue3.setClients(10L);
        queue3.setFullstatus(QueueDTO.status.LOW);
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
    public ResponseEntity<QueueDTO>getQueueById(@PathVariable(name = "id") long id){
        QueueDTO queue1 = new QueueDTO();
        queue1.setId(1L);
        queue1.setClients(19L);
        queue1.setAgents(32L);
        return ResponseEntity.ok(queue1);
    }
}
