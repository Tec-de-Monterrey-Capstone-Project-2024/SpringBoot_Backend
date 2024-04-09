package com.springboot.connectmate.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Schema(
        name = "General Suggestion",
        description = "DTO for general suggestion"
)
public class SuggestionDTO {
    @Schema(
            name = "Identifier (ID)",
            description = "Primary key (PK) for the entity of Suggestions",
            example = "1"
    )
    private Long id;

    public enum SuggestionTopic{AGENT, QUEUE, OTHER}

    @Schema(
            name = "Type",
            description = "Type of the suggestion",
            example = "QUEUE"
    )
    private SuggestionTopic type;

    public enum SuggestionStatus{TODO, DONE}

    @Schema(
            name = "Status",
            description = "Status of the Suggestion (To Do or Done)",
            example = "TODO"
    )
    private SuggestionStatus status;

    @Schema(
            name = "Description",
            description = "Text giving the title of the suggestion",
            example = "Not enough people on virtual floor"
    )
    private String description;

    @Schema(
            name = "Details",
            description = "API's url giving the details for that suggestion",
            example = "http://localhost:8080/api/suggestion/1"
    )
    private String details;

    @Schema(
            name = "Created At",
            description = "Created At timestamp of the Suggestion",
            example = "2021-12-31T23:59:59"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(
            description = "Updated At timestamp of the Suggestion",
            example = "2021-12-31T23:59:59"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}
