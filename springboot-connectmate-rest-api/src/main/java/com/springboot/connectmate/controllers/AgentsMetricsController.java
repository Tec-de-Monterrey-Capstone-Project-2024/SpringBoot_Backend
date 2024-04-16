package com.example.demo.controller;

import com.example.demo.dto.EP5_DTO;
import com.example.demo.dto.AgentDetailsDTO;
import com.example.demo.dto.EP6_DTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/agents")
public class AgentsMetricsController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Métricas del agente encontradas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EP5_DTO.class))}),
            @ApiResponse(responseCode = "404", description = "Agente no encontrado")
    })
    @Operation(summary = "Endpoint número 5, obtiene las métricas de un agente individual")
    @GetMapping("/{id}/metrics")
    public ResponseEntity<List<EP5_DTO>> getAgentMetrics(@PathVariable int id) {

        List<EP5_DTO> metrics = new ArrayList<>();

        EP5_DTO metric1 = new EP5_DTO();
        metric1.setId(id);
        metric1.setName("Service Level");
        metric1.setDescription("Calls answered within");
        metric1.setLimitInSeconds(20);
        metric1.setAnsweredCalls(80);
        metric1.setTotalCalls(90);


        metrics.add(metric1);


        return ResponseEntity.ok(metrics);
    }

    @Operation(summary = "Endpoint número 6, proporciona los KPI's de un agente ")
    @GetMapping("/{agentId}/metrics/{metricId}")
    public ResponseEntity<Object> getMetricDetail(@PathVariable int agentId, @PathVariable int metricId) {

        AgentDetailsDTO AgentDetails = new AgentDetailsDTO();
        AgentDetails.setAgentId(agentId);
        AgentDetails.setDescription("KPI's");

        EP6_DTO KPI1 = new EP6_DTO();
        KPI1.setId(1);
        KPI1.setSL(80);

        EP6_DTO KPI2 = new EP6_DTO();
        KPI2.setId(2);
        KPI2.setACR(3);

        EP6_DTO KPI3 = new EP6_DTO();
        KPI3.setId(3);
        KPI3.setFCR(80);

        EP6_DTO KPI4 = new EP6_DTO();
        KPI4.setId(4);
        KPI4.setOCCUPANCY(85);

        EP6_DTO KPI5 = new EP6_DTO();
        KPI5.setId(5);
        KPI5.setSchedule_Adherence(95);

        EP6_DTO KPI6 = new EP6_DTO();
        KPI6.setId(6);
        KPI6.setASA(30);

        EP6_DTO KPI7 = new EP6_DTO();
        KPI7.setId(7);
        KPI7.setAHA(240);

        AgentDetails.setKPIs(Arrays.asList(KPI1, KPI2, KPI3, KPI4, KPI5, KPI6,KPI7));
        return ResponseEntity.ok(AgentDetails);
    }
}



