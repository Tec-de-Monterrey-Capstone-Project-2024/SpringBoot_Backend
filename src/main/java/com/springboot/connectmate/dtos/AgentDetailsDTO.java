package com.springboot.connectmate.dtos;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;


@Data
public class AgentDetailsDTO {

    @Schema(
            description = "Agent id",
            example = "1"
    )
    private int AgentId;
    
    @Schema(
            description = "KPI",
            example = "Service Level/ Occupancy/ Schedule Adherance"
    )
    private String Description;

    @Schema(
            description = "List of KPI's",
            example = " {"id": 1,"sl": 80},{"id": 2,"acr": 3},{"id":3,"fcr":80},{"id":4,"occupancy":85},{"id":5,"schedule_Adherence":95},{"id":6,"asa":30},{"id":7,"aht":240},{"id":8,"acw":100}"
    )
    private List<KPIsDTO> KPIs;

}