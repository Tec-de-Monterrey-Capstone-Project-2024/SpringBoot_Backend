package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

//Este DTO trae todos los KPIs del Agente
@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class KPIsDTO {
    private int id;
    private int SL;
    private int ACR;
    private int FCR;
    private int OCCUPANCY;
    private int Schedule_Adherence;
    private int ASA;
    private int AHT;
    private int ACW;


}
