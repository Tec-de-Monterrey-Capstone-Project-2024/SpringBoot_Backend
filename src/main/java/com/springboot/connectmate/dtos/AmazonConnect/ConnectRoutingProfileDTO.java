package com.springboot.connectmate.dtos.AmazonConnect;

import lombok.Data;

@Data
public class ConnectRoutingProfileDTO {
    private String id;
    private String arn;
    private String name;
    private String lastModifiedTime;
    private String lastModifiedRegion;
}
