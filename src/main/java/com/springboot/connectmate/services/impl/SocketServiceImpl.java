package com.springboot.connectmate.services.impl;

import com.corundumstudio.socketio.SocketIOClient;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachInsightDetailDTO;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachInsightGenericDTO;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import com.springboot.connectmate.services.SocketService;
import com.springboot.connectmate.services.ThresholdBreachInsightService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.Socket;

@Service
public class SocketServiceImpl implements SocketService {
    private final ThresholdBreachInsightService insightService;
    private final ModelMapper mapper;

    @Autowired
    public SocketServiceImpl(ThresholdBreachInsightService insightService, ModelMapper mapper) {
        this.insightService = insightService;
        this.mapper = mapper;
    }

    // Send object
    public void sendSocketInsight(SocketIOClient senderClient, ThresholdBreachInsight insight) {
        for(SocketIOClient client: senderClient.getNamespace().getAllClients()){
            if(!client.getSessionId().equals(senderClient.getSessionId())){
                client.sendEvent("new_insight", insight);
            }
        }
    }

    public void sendSocketInsight(SocketIOClient senderClient, ThresholdBreachInsightGenericDTO genericInsight) {
        for(SocketIOClient client: senderClient.getNamespace().getAllClients()){
            if(!client.getSessionId().equals(senderClient.getSessionId())){
                client.sendEvent("new_insight", genericInsight);
            }
        }
    }

    public void sendInsight(SocketIOClient senderClient, ThresholdBreachInsightGenericDTO genericInsight) {
        sendSocketInsight(senderClient, genericInsight);
    }

    public void sendInsight(SocketIOClient senderClient, ThresholdBreachInsightDetailDTO insightDTO) {
        ThresholdBreachInsight insight = mapper.map(insightDTO, ThresholdBreachInsight.class);
        sendSocketInsight(senderClient, insight);
    }

    public void sendInsight(SocketIOClient senderClient, ThresholdBreachInsight insight) {
        sendSocketInsight(senderClient, insight);
    }
}