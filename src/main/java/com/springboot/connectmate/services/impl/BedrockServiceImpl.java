package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.services.BedrockService;
import org.springframework.ai.bedrock.titan.BedrockTitanChatClient;
import org.springframework.ai.bedrock.titan.api.TitanChatBedrockApi;
import org.springframework.stereotype.Service;

@Service
public class BedrockServiceImpl implements BedrockService {

    private final BedrockTitanChatClient bedrockTitanChatClient;

    public BedrockServiceImpl(BedrockTitanChatClient bedrockTitanChatClient) {
        this.bedrockTitanChatClient = bedrockTitanChatClient;
    }


}
