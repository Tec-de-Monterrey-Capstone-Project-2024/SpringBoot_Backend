package com.springboot.connectmate.dtos.AmazonConnect;

import com.amazonaws.services.connect.model.InstanceStatus;
import com.amazonaws.services.connect.model.DirectoryType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(
        name = "ConnectInstanceDTO",
        description = "Data Transfer Object (DTO) that summarizes the information of Amazon Connect" +
                "instances associated with an account in Amazon Web Services (AWS). It's intended " +
                "that every call center uses one instance"
)
public class ConnectInstanceDTO {
    @Schema(
            description =  "Identifier (id) of the instance, in this case is a string of " +
                    "hexadecimal numbers separated by '-' (not a number)",
            example = "7c78bd60-4a9f-40e5-b461-b7a0dfaad848"
    )
    private String id;

    @Schema(
            description = "Amazon Resource Name (arn) of the instance. It's a string composed by the " +
                    "type of the instance, the aws region, and the identifier (id) of the " +
                    "instance separated by ':'",
            example = "arn:aws:connect:us-east-1:674530197385:instance/7c78bd60-4a9f-40e5-b461-b7a0dfaad848"
    )
    private String arn;

    @Schema(
            description = "Source for authenticate users inside of the instance " +
                    "(identity management type for the instance)",
            examples = {"SAML", "CONNECT_MANAGEMENT", "EXISTING_DIRECTORY"}
    )
    private DirectoryType identityManagementType;

    @Schema(
            description = "The name of the instance. It's a string with a miminum length of 1 character " +
                    "and a maximum length of 45 characters",
            pattern = "^(?!d-)([\\da-zA-Z]+)([-]*[\\da-zA-Z])*$",
            example = "connectmate"
    )
    private String instanceAlias;

    @Schema(
            description = "When the instance was created",
            example = "2024-05-03T23:09:10.000+00:00"
    )
    private Date createdTime;

    @Schema(
            description = "The service role of the instance",
            example = "arn:aws:iam::674530197385:role/aws-service-role/connect.amazonaws.com/AWSServiceRoleForAmazonConnect_9wtZA7Tow0YOaFA4675H"
    )
    private String serviceRole;


    private InstanceStatus instanceStatus;
    private Boolean inboundCallsEnabled;
    private Boolean outboundCallsEnabled;
    private String instanceAccessUrl;
}
