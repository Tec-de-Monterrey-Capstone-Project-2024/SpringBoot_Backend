package com.springboot.connectmate.services;

import com.corundumstudio.socketio.SocketIOClient;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachInsightDetailDTO;
import com.springboot.connectmate.models.ThresholdBreachInsight;

public interface SocketService {
    // Internal use
    void sendSocketInsight(SocketIOClient senderClient, ThresholdBreachInsight insight);

    // Send Insight (2 varieties)
    void sendInsight(SocketIOClient senderClient, ThresholdBreachInsightDetailDTO insightDTO);
    void sendInsight(SocketIOClient senderClient, ThresholdBreachInsight insight);
}
