package com.springboot.connectmate.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.InsightFieldsDTO;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.KPIDataContextDTO;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachFieldsDTO;
import com.springboot.connectmate.enums.InsightCategory;
import com.springboot.connectmate.enums.InsightResponseField;
import com.springboot.connectmate.enums.InsightSeverity;
import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.services.BedrockService;
import com.springboot.connectmate.services.EmailService;
import com.springboot.connectmate.services.SnsService;
import com.springboot.connectmate.services.ThresholdBreachInsightService;
import org.springframework.ai.bedrock.titan.BedrockTitanChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BedrockServiceImpl implements BedrockService {

    private final BedrockTitanChatClient bedrockTitanChatClient;
    private final ThresholdBreachInsightService thresholdBreachInsightService;
    private final EmailService emailService;
    private final SnsService snsService;
    private final ObjectMapper objectMapper;

    public BedrockServiceImpl(
            BedrockTitanChatClient bedrockTitanChatClient,
            ThresholdBreachInsightService thresholdBreachInsightService,
            EmailService emailService,
            SnsService snsService,
            ObjectMapper objectMapper) {
        this.bedrockTitanChatClient = bedrockTitanChatClient;
        this.thresholdBreachInsightService = thresholdBreachInsightService;
        this.emailService = emailService;
        this.snsService = snsService;
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
    public void generateInsight(
            Metric metric,
            ThresholdBreachFieldsDTO thresholdBreachFields,
            String typeName) {

        // Construct the KPI Data Context Object
        KPIDataContextDTO kpiDataContext = new KPIDataContextDTO(
                metric.getCode().getName(),
                metric.getCode().getDescription(),
                metric.getCode().getAdditionalInfo(),
                typeName,
                metric.getMinimumThresholdValue(),
                metric.getMaximumThresholdValue(),
                metric.getTargetValue(),
                thresholdBreachFields.getValue(),
                thresholdBreachFields.getConnectItemType().getName(),
                thresholdBreachFields.getConnectItemType().getAdditionalInfo()

        );

        // Call the Bedrock service to create an Insight Fields Object with the data context
        InsightFieldsDTO insightFields = createInsight(kpiDataContext);

        // Save the ThresholdBreachInsight Record to the database
        thresholdBreachInsightService.saveInsight(metric, thresholdBreachFields, insightFields);

        // Send an Alert Email
        // TODO: Get the email from the user's profile
        String toEmail = "jose.aram.mendez@gmail.com";
        String subject = "ConnectMate Alert Notification";
        String template = "alert-template"; // This corresponds to alert-template.html
        //To make any changes or to add variables we need to do it in a Map
        //Depending on what variables are on the template, we pass it in the map
        //For testing im using sendEmail endpoint
        Map<String, Object> variables = new HashMap<>();
        variables.put("title", insightFields.getInsightName());
        variables.put("message", insightFields.getInsightSummary());
        emailService.sendAlertEmail(toEmail, subject, template, variables);

        // Send an Alert SNS Message
        // TODO: Get the phone number from the user's profile
        snsService.sendSms("+523525231067", "ConnectMate Alert Notification: " + insightFields.getInsightName());
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
        for (InsightResponseField field : InsightResponseField.values()) {
            // Call the Bedrock service with the constructed message (prompt + KPI Data Context)
            String response = generate(field.getPrompt() + " " + jsonDataContext);
            // Populate the InsightFieldsDTO object with the response
            populateInsight(insight, field, response);
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
    private String findEnumInJsonResponse(String json, List<String> words) {
        String upperCaseJson = json.toUpperCase();
        return words.parallelStream()
                .filter(upperCaseJson::contains)
                .findFirst()
                .orElse(null);
    }

    private void populateInsight(InsightFieldsDTO insight, InsightResponseField field, String response) {
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

        switch (field) {
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
            case ACTIONS:
                insight.setInsightActions(cleanResponse);
                break;
            case CATEGORY:
                List<String> categories = Arrays.asList("CRITICAL", "UNSATISFACTORY", "BELOW_EXPECTATIONS", "EXCEEDS_EXPECTATIONS", "OUTSTANDING", "PIONEERING");
                String foundCategory = findEnumInJsonResponse(cleanResponse, categories);
                insight.setInsightCategory( InsightCategory.fromString(foundCategory) );
                break;
            case SEVERITY:
                List<String> severities = Arrays.asList("LOW", "MEDIUM", "HIGH", "CRITICAL");
                String severityFound = findEnumInJsonResponse(cleanResponse, severities);
                insight.setInsightSeverity( InsightSeverity.fromString(severityFound) );
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
