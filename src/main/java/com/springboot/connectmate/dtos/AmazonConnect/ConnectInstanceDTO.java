package com.springboot.connectmate.dtos.AmazonConnect;

import lombok.Data;

@Data
public class ConnectInstanceDTO {
    private String id;
    private String arn;
    private String identityManagementType;
    private String instanceAlias;
    private String createdTime;
    private String serviceRole;
    private String instanceStatus;
    private boolean inboundCallsEnabled;
    private boolean outboundCallsEnabled;
    private String instanceAccessUrl;
}

