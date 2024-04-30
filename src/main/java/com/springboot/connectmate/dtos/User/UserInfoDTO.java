package com.springboot.connectmate.dtos.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
        name = "UserInfo",
        description = "DTO for User Information"
)
public class UserInfoDTO {

    @Schema(
            description = "First Name of the User",
            example = "John"
    )
    private String firstName;

    @Schema(
            description = "Last Name of the User",
            example = "Doe"
    )
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}