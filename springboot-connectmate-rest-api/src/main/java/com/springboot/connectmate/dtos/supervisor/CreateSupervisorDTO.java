package com.springboot.connectmate.dtos.supervisor;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CreateSupervisorDTO extends SupervisorDTO {
    private String password;
    private String instanceId;
}

