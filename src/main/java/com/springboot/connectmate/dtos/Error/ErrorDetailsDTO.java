package com.springboot.connectmate.dtos.Error;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;


@AllArgsConstructor // Lombok annotation to create a constructor with all the required fields
@Getter // Lombok annotation to create all the getters
public class ErrorDetailsDTO {
    private Date timestamp;
    private String message;
    private String details;

}
