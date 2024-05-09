package com.springboot.connectmate.dtos.AmazonConnect;

import lombok.Data;

@Data
public class ConnectQueueDTO {
    private String id;
    private String arn;
    private String name;
    private String queueType;
    private String lastModifiedTime;
    private String lastModifiedRegion;
}