package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Insight.InsightDTO;
import com.springboot.connectmate.dtos.Insight.InsightStatusUpdateDTO;
import com.springboot.connectmate.enums.InsightStatus;

import java.util.List;

public interface InsightService {
    InsightDTO getInsightByBreachId(Long breachId);

    List<InsightDTO> getQueueInsights();
    void updateInsightStatus(Long id, InsightStatus status);

}
