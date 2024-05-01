package com.springboot.connectmate.dtos.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "UserInfoDTO", description = "Data Transfer Object for User Information")
public class UserInfoDTO {

    @Schema(description = "First name of the user", example = "John")
    private String firstName;

    @Schema(description = "Last name of the user", example = "Doe")
    private String lastName;
}
