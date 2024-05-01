package com.springboot.connectmate.dtos.Queue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "Queue",
        description = "DTO for Queue"
)
public class QueueDTO {

    @Schema(
            description = "ID of the Queue",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Name of the Queue",
            example = "Reimbursements Queue"
    )
    private String name;

    @Schema(
            description = "Number of agents in the Queue",
            example = "10"
    )
    private Long numberOfAgents;

    @Schema(
            description = "Number of clients in the Queue",
            example = "10"
    )
    private Long numberOfClients;

}

