package com.springboot.connectmate.dtos.AmazonConnect;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "ConnectQueueDTO",
        description = "Data Transfer Object (DTO) for queue into a specified Amazon Connect instance"
)
public class ConnectQueueDTO {
    @Schema(
            description = "Identifier (id) of the queue",
            example = "0b777196-086d-46b6-ac21-6b145e65baad"
    )
    private String id;
    private String arn;
    private String name;
    private String queueType;
    private String lastModifiedTime;
    private String lastModifiedRegion;
}
