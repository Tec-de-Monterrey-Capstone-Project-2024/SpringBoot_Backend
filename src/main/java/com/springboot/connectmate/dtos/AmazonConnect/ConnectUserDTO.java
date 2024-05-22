package com.springboot.connectmate.dtos.AmazonConnect;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(
        name = "ConnectUserDTO",
        description = "Data Transfer Object (DTO) for users in a specified Amazon Connect instance"
)
public class ConnectUserDTO {
    @Schema(
            description = "Identifier (id) of the user, in this case is a string of " +
                          "hexadecimal numbers separated by '-' (not a number)",
            example = "ed1ad50d-2ffc-44ad-a565-71f13ad991a5"
    )
    private String id;

    @Schema(
            description = "Amazon Resource Name (arn) of the user account for the Amazon Connect instance. " +
                          "It's a string composed by the type of the instance (connect), the AWS region, " +
                          "the AWS account ID, the instance ID and the identifier (id) for the user in " +
                          "the Amazon Connect instance (agent) separated by ether ':' or '/'",
            example = "arn:aws:connect:us-east-1:674530197385:instance/7c78bd60-4a9f-40e5-b461-b7a0dfaad848/agent/ed1ad50d-2ffc-44ad-a565-71f13ad991a5"
    )
    private String arn;

    @Schema(
            description = "The Amazon Connect user name of the user account (the minimum length " +
                          "must be of 1 character and the maximum length of 100 characters)",
            example = "aram_connectmate"
    )
    private String username;

    @Schema(
            description = "The timestamp when this resource was last modified",
            type = "Timestamp",
            pattern =  "EEE MMM dd HH:mm:ss zzz yyyy",
            example = "Tue May 14 17:55:24 CST 2024"
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
