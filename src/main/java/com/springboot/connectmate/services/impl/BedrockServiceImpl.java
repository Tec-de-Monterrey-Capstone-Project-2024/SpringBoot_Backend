package com.springboot.connectmate.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.InsightFieldsDTO;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.KPIDataContextDTO;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.enums.ResponseField;
import com.springboot.connectmate.models.Metric;
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
    private final ObjectMapper objectMapper;

    public BedrockServiceImpl(BedrockTitanChatClient bedrockTitanChatClient, ObjectMapper objectMapper) {
        this.bedrockTitanChatClient = bedrockTitanChatClient;
        this.objectMapper = objectMapper;
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

    /**
     * Creates an Insight Fields Object by calling the Bedrock service with the KPI Data Context
     */
    @Override
    public InsightFieldsDTO createInsight(KPIDataContextDTO dataContext) {
        // Map the KPI Data Context Object to a JSON string
        String jsonDataContext;
        try {
            jsonDataContext = objectMapper.writeValueAsString(dataContext);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting KPI Data to JSON", e);
        }

        // New InsightFieldsDTO object to store the response from the Bedrock service
        InsightFieldsDTO insight = new InsightFieldsDTO();

        // Iterate through the ResponseField enum to call the Bedrock service with each field and its corresponding prompt
        for (ResponseField responseField : ResponseField.values()) {
            // Call the Bedrock service with the constructed message (prompt + KPI Data Context)
            String response = generate(responseField.getPrompt() + " " + jsonDataContext);
            // Populate the InsightFieldsDTO object with the response
            populateInsight(insight, responseField, response);
        }
        return insight;
    }

    /**
     * Finds the first word in the input string that matches the pattern
     */
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

    /**
     * Finds the first word in the list that is contained in the JSON string
     */
    private String findInJson(String json, List<String> words) {
        for (String word : words) {
            if (json.contains(word)) {
                return word;
            }
        }
        return null;
    }

    private void populateInsight(InsightFieldsDTO insight, ResponseField responseField, String response) {
        // Clean the response string by removing unnecessary characters
        String cleanResponse = response.replaceAll("Generation\\{assistantMessage=AssistantMessage\\{content='", "")
                .replaceAll("', properties=\\{\\}, messageType=ASSISTANT\\}, chatGenerationMetadata=null\\}", "")
                .replaceAll("\\r", "")
                .replaceAll("\\n", "")
                .replaceAll("\\t", "")
                .trim();

        // Remove the field name or any other introductory text from the response
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
