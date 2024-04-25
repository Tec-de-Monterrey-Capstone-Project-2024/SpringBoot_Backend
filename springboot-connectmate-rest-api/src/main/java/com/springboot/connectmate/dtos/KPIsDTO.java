package com.springboot.connectmate.dtos;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

//Este DTO trae todos los KPIs del Agente
@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class KPIsDTO {

    @Schema(
            description = "Id of the KPI",
            example = "1"
    )
    private int id;

    @Schema(
            description = "KPI Service level",
            example = "80"
    )
    private int SL;

    @Schema(
            description = "KPI Abandon Call Rate",
            example = "3"
    )
    private int ACR;

    @Schema(
            description = "KPI First Call Resolution",
            example = "80"
    )
    private int FCR;

    @Schema(
            description = "KPI OCCUPANCY",
            example = "85"
    )
    private int OCCUPANCY;

    @Schema(
            description = "KPI Schedule Adherence",
            example = "95"
    )
    private int Schedule_Adherence;

    @Schema(
            description = "KPI Average Speed of Answer",
            example = "30"
    )
    private int ASA;

    @Schema(
            description = "KPI Average Handling Time",
            example = "240"
    )
    private int AHT;

    @Schema(
            description = "KPI After Call Work",
            example = "100"
    )
    private int ACW;


}
