package com.example.demo.dto;

import java.util.List;

//Este DTO Trae toda el ID del agente para que en el EP6 se organice todos los KPIs con el agente
public class AgentDetailsDTO {
    private int AgentId;
    private String Description;

    private List<EP6_DTO> KPIs;

    public int getAgentId() {
        return AgentId;
    }

    public String getDescription() {
        return Description;
    }

    public List<EP6_DTO> getKPIs() {
        return KPIs;
    }

    public void setAgentId(int agentId) {
        AgentId = agentId;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setKPIs(List<EP6_DTO> KPIs) {
        this.KPIs = KPIs;
    }
}