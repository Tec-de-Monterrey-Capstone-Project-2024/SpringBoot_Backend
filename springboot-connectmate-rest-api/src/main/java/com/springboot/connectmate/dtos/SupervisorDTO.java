package com.springboot.connectmate.dtos;

import lombok.Data;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods for us
public class SupervisorDTO {
    private Long id;
    private String name;
    private String email;
    // TO DO Password is intentionally omitted to not expose it through DTO
    private String password;
    private String phone;
    private String address;
    // TO DO Instance Id is intentionally omitted to not expose it through DTO
    private String instanceId;
}
