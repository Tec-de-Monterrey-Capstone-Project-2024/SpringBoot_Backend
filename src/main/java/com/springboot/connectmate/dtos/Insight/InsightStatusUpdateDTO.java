package com.springboot.connectmate.dtos.Insight;

import com.springboot.connectmate.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "Insight",
        description = "DTO for the insight to change status"
)
public class InsightStatusUpdateDTO {


    @Schema(
            description = "Status of the Insight",
            example = "TO_DO"
    )
    private Status newStatus;
}
