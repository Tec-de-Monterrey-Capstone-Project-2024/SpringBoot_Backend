package com.springboot.connectmate.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.enums.ResponseField;
import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.InsightDTO;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.KpiDataDTO;
import com.springboot.connectmate.services.BedrockService;
import org.springframework.ai.bedrock.titan.BedrockTitanChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BedrockServiceImpl implements BedrockService {

    private final BedrockTitanChatClient bedrockTitanChatClient;

    public BedrockServiceImpl(BedrockTitanChatClient bedrockTitanChatClient) {
        this.bedrockTitanChatClient = bedrockTitanChatClient;
    }


    @Override
    public String generate(String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return bedrockTitanChatClient.call(prompt).getResult().toString();
    }

    @Override
    public Flux<ChatResponse> generateStream(String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return bedrockTitanChatClient.stream(prompt);
    }

    @Override
    public void generateInsight(Metric metric, Double metricValue, ConnectMetricType metricType, String typeId) {

    }

    @Override
    public InsightDTO createInsight(KpiDataDTO kpiDataDTO) {
        String kpiDataJson = generateKpiDataJson(kpiDataDTO);
        InsightDTO insight = new InsightDTO();

        for (ResponseField responseField : ResponseField.values()) {
            String response = callBedrockService(responseField.getPrompt(), kpiDataJson);
            populateInsight(insight, responseField, response);
        }
        return insight;
    }

    private String generateKpiDataJson(KpiDataDTO kpiDataDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(kpiDataDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al convertir KPI Data a JSON", e);
        }
    }

    private String callBedrockService(String prompt, String kpiDataJson) {
        String message = prompt + " " + kpiDataJson;
        return generate(message);
    }

    private String findTitleName(String patternString, String input) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            System.out.println("No match found in: " + input);
            return null;
        }
    }

    private String findInJson(String json, List<String> words) {
        for (String word : words) {
            if (json.contains(word)) {
                return word;
            }
        }
        return null;
    }

    private void populateInsight(InsightDTO insight, ResponseField responseField, String response) {
        String cleanResponse = response.replaceAll("Generation\\{assistantMessage=AssistantMessage\\{content='", "")
                .replaceAll("', properties=\\{\\}, messageType=ASSISTANT\\}, chatGenerationMetadata=null\\}", "")
                .replaceAll("\\r", "")
                .replaceAll("\\n", "")
                .replaceAll("\\t", "")
                .trim();

        int colonIndex = cleanResponse.indexOf(":");
        if (colonIndex != -1) {
            cleanResponse = cleanResponse.substring(colonIndex + 1).trim();
        }

        switch (responseField) {
            case NAME:
                String name = findTitleName("is \\\"(.*?)\\\"", cleanResponse);
                if (name != null) {
                    insight.setInsightName(name);
                } else {
                    insight.setInsightName("No name found");
                }
                break;
            case SUMMARY:
                insight.setInsightSummary(cleanResponse);
                break;
            case DESCRIPTION:
                insight.setInsightDescription(cleanResponse);
                break;
            case ACTION:
                insight.setInsightActions(cleanResponse);
                break;
            case CATEGORY:
                List<String> category = Arrays.asList("LOW", "MEDIUM", "HIGH");
                String categoryFound = findInJson(cleanResponse, category);
                if (categoryFound != null) {
                    insight.setInsightCategory(categoryFound);
                } else {
                    insight.setInsightCategory("Unknown");
                }
                break;
            case PERFORMANCE:
                List<String> performance = Arrays.asList("CRITICAL", "UNSATISFACTORY", "BELOW_EXPECTATIONS", "EXCEEDS_EXPECTATIONS", "OUTSTANDING", "PIONEERING");
                String foundPerformance = findInJson(cleanResponse, performance);
                if (foundPerformance != null) {
                    insight.setInsightPerformance(foundPerformance);
                } else {
                    insight.setInsightPerformance("Unknown");
                }
                break;
            case ROOT_CAUSE:
                insight.setInsightRootCause(cleanResponse);
                break;
            case IMPACT:
                insight.setInsightImpact(cleanResponse);
                break;
            case PREVENTION:
                insight.setInsightPrevention(cleanResponse);
                break;
        }
    }


}
