package com.springboot.connectmate.dtos;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

//This DTO brings all the Agent KPIs
@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class KPIsDTO {

    @Schema(
            description = "Id of the KPI",
            example = "1"
    )
    private long id;

    @Schema(
            description = "KPI Service level",
            example = "80"
    )
    private double SL;

    @Schema(
            description = "KPI Abandon Call Rate",
            example = "3"
    )
    private double ACR;

    @Schema(
            description = "KPI First Call Resolution",
            example = "80"
    )
    private double FCR;

    @Schema(
            description = "KPI OCCUPANCY",
            example = "85"
    )
    private double OCCUPANCY;

    @Schema(
            description = "KPI Schedule Adherence",
            example = "95"
    )
    private double Schedule_Adherence;

    @Schema(
            description = "KPI Average Speed of Answer",
            example = "30"
    )
    private double ASA;

    @Schema(
            description = "KPI Average Handling Time",
            example = "240"
    )
    private double AHT;

    @Schema(
            description = "KPI After Call Work",
            example = "100"
    )
    private double ACW;


}
