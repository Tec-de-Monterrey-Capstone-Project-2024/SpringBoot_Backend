package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.services.BedrockService;
import org.springframework.ai.bedrock.titan.BedrockTitanChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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

}
