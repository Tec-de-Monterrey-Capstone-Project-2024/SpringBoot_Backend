package com.springboot.connectmate.dtos.Insight;

import com.springboot.connectmate.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateStatusFormDTO {

    @Schema(
            description = "New status for the insight",
            examples = {"TO_DO", "IN_PROGRESS", "DONE"}
    )
    // @NotBlank(message = "Status cannot be blank")
    private Status status;
}
