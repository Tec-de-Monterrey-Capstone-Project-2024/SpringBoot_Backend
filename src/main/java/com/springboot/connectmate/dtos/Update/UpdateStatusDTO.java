package com.springboot.connectmate.dtos.Update;

import com.springboot.connectmate.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO for updating the status of a Threshold Breach Insight")
public class UpdateStatusDTO {

    @Schema(description = "The new status to set", example = "ACTIVE")
    private Status status;
}
