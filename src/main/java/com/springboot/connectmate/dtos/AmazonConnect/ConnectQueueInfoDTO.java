package com.springboot.connectmate.dtos.AmazonConnect;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Schema(
        name = "ConnectQueueInfoDTO",
        description = "Data Transfer Object (DTO) for the information of a queue of a specified Amazon Connect instance."
)
public class ConnectQueueInfoDTO {
    @Schema(
            description = "Set with all the ids of the users in the queue.",
            example = "40d74e54-2e2d-42f2-87f9-96d6d6abd97c"
    )
    private Set<String> users = new HashSet<>();
    @Schema(
            description = "Number of clients in that queue.",
            example = "3"
    )
    private int contactCount;
}
