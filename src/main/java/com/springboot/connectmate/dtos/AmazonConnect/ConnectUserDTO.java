package com.springboot.connectmate.dtos.AmazonConnect;

import lombok.Data;

@Data
public class ConnectUserDTO {
    private String id;
    private String arn;
    private String username;
    private String lastModifiedTime;
    private String lastModifiedRegion;
    private String hierarchyGroupId;
}
