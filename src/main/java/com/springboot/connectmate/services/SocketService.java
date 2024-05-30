package com.springboot.connectmate.services;

import com.corundumstudio.socketio.SocketIOClient;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachInsightDetailDTO;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SocketService {
    private final ThresholdBreachInsightService insightService;
    private final ModelMapper mapper;

    @Autowired
    public SocketService(ThresholdBreachInsightService insightService, ModelMapper mapper) {
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

    public void sendInsight(SocketIOClient senderClient, ThresholdBreachInsightDetailDTO insightDTO) {
        ThresholdBreachInsight insight = mapper.map(insightDTO, ThresholdBreachInsight.class);
        sendSocketInsight(senderClient, insight);
    }

    public void sendInsight(SocketIOClient senderClient, ThresholdBreachInsight insight) {
        sendSocketInsight(senderClient, insight);
    }
}
