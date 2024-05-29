package com.springboot.connectmate.dtos.AmazonConnect;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "ConnectContactDTO",
        description = "Data Transfer Object (DTO) for a contact of a specified Amazon Connect instance."
)
public class ConnectContactDTO {
    @Schema(
            description = "Id of the contact",
            example = "40d74e54-2e2d-42f2-87f9-96d6d6abd97c"
    )
    private String contactId;

    @Schema(
            description = "Queue of the contact",
            example = "Enabled"
    )
    private String queueId;
}
