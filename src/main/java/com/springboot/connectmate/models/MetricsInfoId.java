package com.springboot.connectmate.models;

import com.springboot.connectmate.enums.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods for us
@AllArgsConstructor // Lombok annotation to create a constructor with all the arguments
@NoArgsConstructor // Lombok annotation to create a constructor with no arguments

public class MetricsInfoId implements Serializable {

    private Code code;
    private Boolean isPositive;

}