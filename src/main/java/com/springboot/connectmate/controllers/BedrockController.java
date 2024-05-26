package com.springboot.connectmate.controllers;

import com.springboot.connectmate.services.BedrockService;
import org.springframework.ai.bedrock.titan.BedrockTitanChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

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
}
