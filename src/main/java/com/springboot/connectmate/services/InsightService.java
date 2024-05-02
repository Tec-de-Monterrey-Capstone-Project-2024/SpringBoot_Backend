package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Insight.InsightDTO;
import com.springboot.connectmate.enums.InsightStatus;

import java.util.List;

public interface InsightService {
    InsightDTO getInsightById(Long insightId);
    InsightDTO getInsightByBreachId(Long breachId);
    List<InsightDTO> getQueueInsights();
    List<InsightDTO> getInsightsByAgentId(Long agentId);
    List<InsightDTO> getAllInsights();
    List<InsightDTO> getInsightsByStatus(InsightStatus status);
    void updateInsightStatus(Long id, InsightStatus newStatus);

}
