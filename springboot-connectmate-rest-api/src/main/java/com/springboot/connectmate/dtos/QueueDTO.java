package com.springboot.connectmate.dtos;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Queue",
        description = "DTO for Queue"
)
public class QueueDTO{
    @Schema(
            description = "Queue ID",
            example = "1"
    )
    private Integer id;
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
    @Schema(
            description = "Fullness in Queue",
            example = "Medium"
    )
    private String status;

}