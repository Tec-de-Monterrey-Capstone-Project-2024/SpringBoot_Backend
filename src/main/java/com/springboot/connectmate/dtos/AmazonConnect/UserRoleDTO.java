package com.springboot.connectmate.dtos.AmazonConnect;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Schema(
        name = "UserRoleDTO",
        description = "Data Transfer Object (DTO) for Agent status in Amazon Connect"
)
public class UserRoleDTO {
    private String roleId;
    private String roleName;

    // Constructors, getters, and setters

    public UserRoleDTO(String roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

}