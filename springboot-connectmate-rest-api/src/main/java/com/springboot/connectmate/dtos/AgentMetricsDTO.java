package com.springboot.connectmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
@Schema(name = "AgentMetrics", description = "DTO for Agent Metrics")
public class AgentMetricsDTO {

    @Schema(description = "Description of metrics", example = "calls answered within")
    private String description;

    @Schema(description = "Limit in seconds for answering calls", example = "20")
    private int limitInSeconds;

    @Schema(description = "Details of time to answer calls")
    private List<CallDetailDTO> timeToAnswerCalls;

    @Data
    @Schema(name = "CallDetail", description = "Details of a call")
    public static class CallDetailDTO {
        private int id;
        private String phoneNumber;
        private int timeInSeconds;

        // Manually defined constructor
        public CallDetailDTO(int id, String phoneNumber, int timeInSeconds) {
            this.id = id;
            this.phoneNumber = phoneNumber;
            this.timeInSeconds = timeInSeconds;
        }
    }
}
