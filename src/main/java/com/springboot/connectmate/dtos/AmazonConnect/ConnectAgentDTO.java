package com.springboot.connectmate.dtos.AmazonConnect;

import com.amazonaws.services.connect.model.AgentStatusType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "ConnectAgentStatusDTO",
        description = "Data Transfer Object (DTO) for Agent status in Amazon Connect"
)
public class ConnectAgentDTO {
    @Schema(
            description = "Identifier (id) of the agent, in this case is a string of " +
                    "hexadecimal numbers separated by '-' (not a number)",
            example = "05619af9-666f-48c4-986a-4e8874942904"
    )
    private String id;

    @Schema(
            description = "The Amazon Resource Name (arm) for the agent status",
            example = "arn:aws:connect:us-east-1:674530197385:instance/7c78bd60-4a9f-40e5-b461-b7a0dfaad848/agent-state/05619af9-666f-48c4-986a-4e8874942904"
    )
    private String arn;

    @Schema(
            description = "The name of the agent status (the minimum length must be of " +
                    "1 character and the maximum length of 127 characters)",

            example = "Available"
    )
    private String name;

    @Schema(
            description = "The status of the agent",
            examples = {"ROUTABLE", "CUSTOM", "OFFLINE"}
    )
    private AgentStatusType type;
}
