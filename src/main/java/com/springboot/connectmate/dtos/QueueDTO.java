package com.springboot.connectmate.dtos;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

@Schema(
        name = "Queue",
        description = "DTO for Queue"
)

@Data // Lombok's annotation to create all the getters, setters, equals, hash, and toString methods for us
public class QueueDTO{
    @Schema(
            description = "Queue ID",
            example = "1"
    )
    private Long id;
    @Schema(
            description = "Number of Agents in Queue",
            example = "32"
    )
    private Long agents;
    @Schema(
            description = "Number of Clients in Queue",
            example = "20"
    )
    private Long clients;

    public enum status {
        LOW, MEDIUM, FULL
    }
    @Schema(
            description = "Fullness in Queue",
            example = "Medium"
    )
    private status fullstatus;
}