package com.springboot.connectmate.services;

import com.corundumstudio.socketio.SocketIOClient;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachInsightDetailDTO;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachInsightGenericDTO;
import com.springboot.connectmate.models.ThresholdBreachInsight;

public interface SocketService {
    // Internal use (2 varieties)
    void sendSocketInsight(SocketIOClient senderClient, ThresholdBreachInsight insight);
    void sendSocketInsight(SocketIOClient senderClient, ThresholdBreachInsightGenericDTO genericInsight);

    // Send Insight (3 varieties)
    void sendInsight(SocketIOClient senderClient, ThresholdBreachInsightGenericDTO genericInsight);
    void sendInsight(SocketIOClient senderClient, ThresholdBreachInsightDetailDTO insightDTO);
    void sendInsight(SocketIOClient senderClient, ThresholdBreachInsight insight);
}