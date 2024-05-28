package com.springboot.connectmate.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.connectmate.dtos.AmazonConnect.InsightDTO;
import com.springboot.connectmate.dtos.AmazonConnect.KpiDataDTO;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
        /**
         * TODO: Generate an insight using the Bedrock Service
         * Get Data from Connect Type (describe user, queue, instance)
         * Get the prompts and generate each field with the data from the metric
         * Adapt parameters as needed
         * Parse Bedrock response and return a DTO Insight Response
         */

        /**
         * ARMANDO: Usa los prompts del enum que hiciste, a la par de los parametros que te llegan
         * y genera un insight con esos datos, para generarlo, una vez que tengas el prompt, solo hazlo
         * como en la funcion de arriba llamada 'generate '
         */

        /**
         * SAM: Parsear las respuestas y guardarlas en un DTO Insight Response,
         * manejar los casos en los que una respuesta no venga o fallos (si aplica)
         */

        /**
         * Gerry: Una vez que tengamos nuestro DTO Insight Response, tenemos que guardar un objeto
         * THresholdBreachInsight en Base De Datos. Con el insight DTO y los parametros de la funcion
         * tendrias todo lo necesario para implementarlo
         */

        /**
         * Al final los 3 juntos, hagan un controller muy basico y prueben que todo esta funcionando correctamente
         * El resultado correcto seria que en base de datos este un registro ThresholdBreachInsight con insights de
         * muy buena calidad
         */
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

        switch (responseField) {
            case NAME:
                String name = findTitleName("is \\\"(.*?)\\\"", cleanResponse);
                if (name != null) {
                    insight.setInsightName(name);
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
                insight.setInsightCategory(categoryFound);
                break;
            case PERFORMANCE:
                List<String> performance= Arrays.asList("CRITICAL", "UNSATISFACTORY", "BELOW_EXPECTATIONS", "EXCEEDS_EXPECTATIONS","OUTSTANDING","PIONEERING");
                String foundPerformance = findInJson(cleanResponse, performance);
                insight.setInsightPerformance(foundPerformance);
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
