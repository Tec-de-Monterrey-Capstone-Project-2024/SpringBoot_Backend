package com.springboot.connectmate.dtos.Insight;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateStatusDTO {

    @Schema(description = "New status for the insight", example = "DONE")
    private String status;
}
