package com.springboot.connectmate.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Schema(
        name = "General Task",
        description = "DTO for general tasks in the Call Center"
)
public class TaskDTO {
    @Schema(
            name = "Identifier (ID)",
            description = "Primary key (PK) for the entity of Tasks",
            example = "1"
    )
    private Long id;

    public enum TaskType{AGENT, QUEUE, OTHER}

    @Schema(
            name = "Type",
            description = "Type of the task",
            example = "QUEUE"
    )
    private TaskType type;

    public enum TaskStatus{TODO, DONE}

    @Schema(
            name = "Status",
            description = "Status of the Task (To Do or Done)",
            example = "TODO"
    )
    private TaskStatus status;

    @Schema(
            name = "Description",
            description = "Text giving the title of the task",
            example = "Not enough people on virtual floor"
    )
    private String description;

    @Schema(
            name = "Details",
            description = "API's url giving the details for the task",
            example = "http://localhost:8080/api/task/1"
    )
    private String details;

    @Schema(
            name = "Created At",
            description = "Created At timestamp of the Task",
            example = "2021-12-31T23:59:59"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(
            description = "Updated At timestamp of the Task",
            example = "2021-12-31T23:59:59"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}
