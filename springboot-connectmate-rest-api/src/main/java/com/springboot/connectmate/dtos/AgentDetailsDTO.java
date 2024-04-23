package com.example.demo.dto;

import java.util.List;
import lombok.Data;


@Data
public class AgentDetailsDTO {
    private int AgentId;
    private String Description;

    private List<KPIsDTO> KPIs;

}
