package com.springboot.connectmate.dtos.AmazonConnect;

import com.amazonaws.services.connect.model.QueueType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(
        name = "ConnectQueueDTO",
        description = "Data Transfer Object (DTO) for queue into a specified Amazon Connect instance"
)
public class ConnectQueueDTO {
    @Schema(
            description = "Identifier (id) of the queue, in this case is a string of " +
                          "hexadecimal numbers separated by '-' (not a number)",
            example = "0b777196-086d-46b6-ac21-6b145e65baad"
    )
    private String id;

    @Schema(
            description = "The Amazon Resource Number (arn) for the queue. It's a string composed " +
                          "by the type of the instance (connect), IAM account ID, ID of the " +
                          "instance and ID of the queue or ID of the agent",
            example = "arn:aws:connect:us-east-1:674530197385:instance/7c78bd60-4a9f-40e5-b461-b7a0dfaad848/queue/0b777196-086d-46b6-ac21-6b145e65baad"
    )
    private String arn;

    @Schema(
            description = "The name of the queue (the minimum length must be of 1 character " +
                          "and the maximum length of 127 characters)",
            example = "FAQ"
    )
    private String name;

    @Schema(
            description = "The type of the queue. It can take 2 values 'STANDARD' for the queues " +
                          "where contacts wait before being routed to agents and 'AGENT' for the " +
                          "queues that every agent response (there is one queue of type 'AGENT' " +
                          "for every agent inside on a 'STANDARD' queue on the Contact Center)",
            examples = {"STANDARD", "AGENT"}
    )
    private QueueType queueType;

    @Schema(
            description = "The timestamp when this resource was last modified",
            type = "Timestamp",
            pattern = "EEE MMM dd HH:mm:ss zzz yyyy",
            example = "Fri May 03 17:29:27 CST 2024"
    )
    @JsonFormat(pattern = "EEE MMM dd HH:mm:ss zzz yyyy")
    private Timestamp lastModifiedTime;

    @Schema(
            description = "The AWS Region where this resource was last modified",
            pattern = "[a-z]{2}(-[a-z]+){1,2}(-[0-9])?",
            example = "us-east-1"
    )
    private String lastModifiedRegion;
}
