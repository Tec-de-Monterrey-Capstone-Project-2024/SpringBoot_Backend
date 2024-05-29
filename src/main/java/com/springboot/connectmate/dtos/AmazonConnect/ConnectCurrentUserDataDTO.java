package com.springboot.connectmate.dtos.AmazonConnect;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(
        name = "ConnectCurrentUserDataDTO",
        description = "Data Transfer Object (DTO) for the current user data of a specified Amazon Connect instance."
)
public class ConnectCurrentUserDataDTO {
    @Schema(
            description = "Id of the agent",
            example = "40d74e54-2e2d-42f2-87f9-96d6d6abd97c"
    )
    private String userId;

    @Schema(
            description = "Status of the queue",
            example = "Enabled"
    )
    private String statusName;

    @Schema(
            description = "List of all the clients on a particular queue.",
            example = "{7d76a01c-674f-431b-94ed-2d9a936ff3e2, 7d76a01c-674f-431b-94ed-2d9a936ff3e2, 7d76a01c-674f-431b-94ed-2d9a936ff3e1}"
    )
    private List<ConnectContactDTO> contacts;
}
