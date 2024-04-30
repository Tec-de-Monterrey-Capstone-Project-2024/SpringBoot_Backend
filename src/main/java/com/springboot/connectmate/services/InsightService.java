package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Insight.InsightDTO;

import java.util.List;

public interface InsightService {
    InsightDTO getInsightById(Long insightId);
    InsightDTO getInsightByBreachId(Long breachId);
    List<InsightDTO> getQueueInsights();
}
