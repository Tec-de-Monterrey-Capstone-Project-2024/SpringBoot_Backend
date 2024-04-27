package com.springboot.connectmate.dtos.OldDTOS;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Schema(
        name = "General Insight",
        description = "DTO for general insights in the Call Center"
)
public class OldInsightDTO {
    @Schema(
            name = "Identifier (ID)",
            description = "Primary key (PK) for the entity of Insights",
            example = "1"
    )
    private Long id;

    public enum InsightType{AGENT, QUEUE, OTHER}

    @Schema(
            name = "Type",
            description = "Type of the insight",
            example = "QUEUE"
    )
    private InsightType type;

    public enum InsightStatus{TODO, DONE}

    @Schema(
            name = "Status",
            description = "Status of the Insight (To Do or Done)",
            example = "TODO"
    )
    private InsightStatus status;

    @Schema(
            name = "Description",
            description = "Text giving the title of the insight",
            example = "Not enough people on virtual floor"
    )
    private String description;

    @Schema(
            name = "Created At",
            description = "Created At timestamp of the Insight",
            example = "2021-12-31T23:59:59"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(
            description = "Updated At timestamp of the Insight",
            example = "2021-12-31T23:59:59"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}
