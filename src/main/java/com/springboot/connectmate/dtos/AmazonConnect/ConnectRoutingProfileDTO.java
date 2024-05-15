package com.springboot.connectmate.dtos.AmazonConnect;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(
        name = "ConnectRoutingProfileDTO",
        description = "Data Transfer Object (DTO) for routing profile in Amazon Connect"
)
public class ConnectRoutingProfileDTO {
    @Schema(
            description = "Identifier (id) of the routing profile, in this case is a string of " +
                          "hexadecimal numbers separated by '-' (not a number)",
            example = "4896ae34-a93e-41bc-8231-bf189e7628b1"
    )
    private String id;

    @Schema(
            description = "The Amazon Resource Number (arn) for the routing profile. It's a string " +
                          "composed by the type of the instance (connect), the AWS region, the AWS " +
                          "account id and the identifier (id) of the routing profile separated by ':'",
            example = "arn:aws:connect:us-east-1:674530197385:instance/7c78bd60-4a9f-40e5-b461-b7a0dfaad848/routing-profile/4896ae34-a93e-41bc-8231-bf189e7628b1"
    )
    private String arn;

    @Schema(
            description = "The name of the routing profile (the minimum length must be of 1 character " +
                          "and the maximum length of 127 characters)",
            example = "Basic Routing Profile"
    )
    private String name;

    @Schema(
            description = "The timestamp when this resource was last modified",
            type = "Timestamp",
            pattern = "EEE MMM dd HH:mm:ss zzz yyyy",
            example = "Mon May 13 00:01:42 CST 2024"
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
