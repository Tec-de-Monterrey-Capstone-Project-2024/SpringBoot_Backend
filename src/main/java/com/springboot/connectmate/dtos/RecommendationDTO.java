package com.springboot.connectmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for Recommendations")
public class RecommendationDTO {
    @Schema(description = "Unique identifier of the recommendation")
    private String id;

    @Schema(description = "Description of the recommendation")
    private String description;
}
