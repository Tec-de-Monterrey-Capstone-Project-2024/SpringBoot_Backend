package com.springboot.connectmate.services;

import com.corundumstudio.socketio.SocketIOClient;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachInsightDetailDTO;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SocketService {
    private final ThresholdBreachInsightService insightService;

    // Send object
    public void sendSocketInsight(
            SocketIOClient senderClient,
            ThresholdBreachInsight insight
    ) {
        for(SocketIOClient client: senderClient.getNamespace().getAllClients()){
            if(!client.getSessionId().equals(senderClient.getSessionId())){
                client.sendEvent("new_insight", insight);
            }
        }
    }

    public void saveInsight(SocketIOClient senderClient, ThresholdBreachInsightDetailDTO insightDTO) {
        ThresholdBreachInsight insight = insightService.saveInsight(insightDTO);
        sendSocketInsight(senderClient, insight);
    }

    public void saveInsight(SocketIOClient senderClient, ThresholdBreachInsight insight) {
        sendSocketInsight(senderClient, insight);
    }
}
