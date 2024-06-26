package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.ThresholdBreachInsight.InsightFieldsDTO;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.KPIDataContextDTO;
import com.springboot.connectmate.enums.ConnectMetricCode;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.services.BedrockService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.ai.bedrock.titan.BedrockTitanChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Hidden
@RestController
public class BedrockController {

    private final BedrockTitanChatClient chatClient;
    private final BedrockService bedrockService;

    @Autowired
    public BedrockController(BedrockTitanChatClient chatClient, BedrockService bedrockService) {
        this.chatClient = chatClient;
        this.bedrockService = bedrockService;
    }

    @GetMapping("/ai/generate")
    public Map generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return Map.of("generation", chatClient.call(message));
    }

    @GetMapping("/ai/generate/no-map")
    public String generateNoMap(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return chatClient.call(message);
    }

    @GetMapping("/ai/generateStream")
    public String testServiceResponse(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return bedrockService.generate(message);
    }

    @ApiResponse(
            responseCode = "200",
            description = "Insight created succesfully ."
    )
    @Operation (
            summary = "Post API for the created Insights ",
            description = "Insight Creation"
    )
    @PostMapping("/ai/createInsight")
    public ResponseEntity<InsightFieldsDTO> createInsight(
            @RequestBody KPIDataContextDTO KPIDataContextDTO)
    {

        InsightFieldsDTO insight = bedrockService.createInsight(KPIDataContextDTO);
        return ResponseEntity.ok(insight);
    }

    @Operation(
            summary = "Creates a ThresholdBreachInsight record",
            description = "Create the thresholdbreachinsight record "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Insight created successfully"
    )
    @PostMapping("/generateAndSaveInsight")
    public ResponseEntity<String> generateAndSaveInsight(
            @RequestBody KPIDataContextDTO KPIDataContextDTO,
            @RequestParam Double metricValue,
            @RequestParam ConnectMetricType metricType,
            @RequestParam String typeId,
            @RequestParam ConnectMetricCode metricCode,
            @RequestParam Status status) {

        InsightFieldsDTO insight = bedrockService.createInsight(KPIDataContextDTO);

        return ResponseEntity.ok("Insight created successfully");
    }
}
