package com.example.demo.controller;
import com.example.demo.dto.MetricAgentsDTO;
import com.example.demo.dto.AgentDetailsDTO;
import com.example.demo.dto.KPIsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/agents")
public class AgentsMetricsController {

    
    @Operation(
            summary = "Get metric agents",
            description = "Gets all the metrics for a supervisor"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the metrics"),
    })
    public List<MetricAgentsDTO> getAllMetrics(){
        return List.of(new MetricAgentsDTO());
    }
    @GetMapping("/{id}/metrics")
    public MetricAgentsDTO getAgentMetrics(@PathVariable int id) {
        MetricAgentsDTO metrics = new MetricAgentsDTO();
        metrics.setId(id);
        metrics.setName("Service Level");
        metrics.setDescription("Calls answered within");
        metrics.setLimitInSeconds(20);
        metrics.setAnsweredCalls(80);
        metrics.setTotalCalls(90);

        return metrics;
    }



    
    @Operation(
            summary = "Gets KPI's for an agent",
            description = "Gets the agents info and the KPI's"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the agent KPI's"),
    })
    
    @GetMapping("/{agentId}/metrics/{metricId}")
    public AgentDetailsDTO getMetricDetail(@PathVariable int agentId, @PathVariable int metricId) {

        AgentDetailsDTO AgentDetails = new AgentDetailsDTO();
        AgentDetails.setAgentId(agentId);
        AgentDetails.setDescription("KPI's");

        KPIsDTO KPI1 = new KPIsDTO();
        KPI1.setId(1);
        KPI1.setSL(80);

        KPIsDTO KPI2 = new KPIsDTO();
        KPI2.setId(2);
        KPI2.setACR(3);

        KPIsDTO KPI3 = new KPIsDTO();
        KPI3.setId(3);
        KPI3.setFCR(80);

        KPIsDTO KPI4 = new KPIsDTO();
        KPI4.setId(4);
        KPI4.setOCCUPANCY(85);

        KPIsDTO KPI5 = new KPIsDTO();
        KPI5.setId(5);
        KPI5.setSchedule_Adherence(95);

        KPIsDTO KPI6 = new KPIsDTO();
        KPI6.setId(6);
        KPI6.setASA(30);

        KPIsDTO KPI7 = new KPIsDTO();
        KPI7.setId(7);
        KPI7.setAHT(240);

        KPIsDTO KPI8 = new KPIsDTO();
        KPI8.setId(8);
        KPI8.setAHT(100);

        AgentDetails.setKPIs(Arrays.asList(KPI1, KPI2, KPI3, KPI4, KPI5, KPI6,KPI7,KPI8));
        return AgentDetails;
    }
}


