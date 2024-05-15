package com.springboot.connectmate.dtos.AmazonConnect;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods for us
@Schema(
        name = "ConnectUserData",
        description = "DTO for User Data obtained from Connect"
)
public class ConnectUserDataDTO {

    @Schema(
            description = "User's Connect Id.",
            example = "b618f4f4-d955-4006-be08-c8a2e8f427bc"
    )
    private String userId;

    @Schema(
            description = "User's Connect Routing Profile Id.",
            example = "4896ae34-a93e-41bc-8231-bf189e7628b2"
    )
    private String routingProfileId;

    @Schema(
            description = "User's current Connect queue Id.",
            example = "f0813607-af92-4a36-91e6-630ababb643d"
    )
    private String queueId;
}