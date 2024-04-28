package com.springboot.connectmate.dtos.Insight;

import com.springboot.connectmate.dtos.Alert.AlertDTO;
import com.springboot.connectmate.enums.InsightStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(
        name = "Insight",
        description = "DTO for Insight"
)
public class InsightDTO {

    @Schema(
            description = "Id of the Insight",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Constructed description of the Insight",
            example = "This is a sample constructed description."
    )
    private String constructedDescription;

    private List<AlertDTO> alerts;

    private String nombre;

    private String summary;

    @Schema(
            description = "Status of the Insight",
            example = "TO_DO"
    )
    private InsightStatus status;
}
