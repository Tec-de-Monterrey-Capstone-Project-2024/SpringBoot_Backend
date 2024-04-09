package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.TaskDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/task")
@Tag(
        name = "Task REST API",
        description = "An API that have the CRUD services for tasks in the Call Center"
)
public class TaskController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Get the tasks successfully",
                    content = {
                        @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDTO.class))
            }),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content
            )
    })
    @Operation(summary = "Get all tasks for the Call Center")
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(){
        List<TaskDTO> response = new ArrayList<>();
        TaskDTO task1 = new TaskDTO();
        task1.setId(1L);
        task1.setType(TaskDTO.TaskType.QUEUE);
        task1.setStatus(TaskDTO.TaskStatus.TODO);
        task1.setDescription("Not enough people on virtual floor.");
        task1.setDetails("http://localhost:8080/api/task/1");
        task1.setCreatedAt(LocalDateTime.now());

        response.add(task1);
        return ResponseEntity.ok(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                description = "Task updated successfully",
                content = {
                    @Content(
                            schema = @Schema(implementation = TaskDTO.class))
            }),
            @ApiResponse(responseCode = "500",
                description = "Internal Server Error",
                content = @Content
            )
    })
    @Operation(summary = "Modify one specific task by its identifier (ID)")
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long taskId, @RequestBody TaskDTO task){
        task.setId(taskId);
        task.setUpdatedAt(LocalDateTime.now());

        return ResponseEntity.ok(task);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "No content"
            ),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content
            )
    })
    @Operation(summary = "Delete one task by Id in the database")
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId){
        return ResponseEntity.noContent().build();
    }

}
