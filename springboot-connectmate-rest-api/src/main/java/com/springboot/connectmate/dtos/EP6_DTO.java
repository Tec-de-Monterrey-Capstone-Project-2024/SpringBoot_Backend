package com.example.demo.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

//Este DTO trae todos los KPIs del Agente
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class EP6_DTO {
    private int id;
    private int SL;
    private int ACR;
    private int FCR;
    private int OCCUPANCY;
    private int Schedule_Adherence;
    private int ASA;
    private int AHA;

    public int getId() {
        return id;
    }

    public int getSL() {
        return SL;
    }

    public int getACR() {
        return ACR;
    }

    public int getFCR() {
        return FCR;
    }

    public int getOCCUPANCY() {
        return OCCUPANCY;
    }

    public int getSchedule_Adherence() {
        return Schedule_Adherence;
    }

    public int getASA() {
        return ASA;
    }

    public int getAHA() {
        return AHA;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSL(int SL) {
        this.SL = SL;
    }

    public void setACR(int ACR) {
        this.ACR = ACR;
    }

    public void setFCR(int FCR) {
        this.FCR = FCR;
    }

    public void setOCCUPANCY(int OCCUPANCY) {
        this.OCCUPANCY = OCCUPANCY;
    }

    public void setSchedule_Adherence(int schedule_Adherence) {
        Schedule_Adherence = schedule_Adherence;
    }

    public void setASA(int ASA) {
        this.ASA = ASA;
    }

    public void setAHA(int AHA) {
        this.AHA = AHA;
    }
}
