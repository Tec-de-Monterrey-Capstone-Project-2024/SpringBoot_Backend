package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Insight.InsightDTO;

public interface InsightService {
    InsightDTO getInsightByBreachId(Long breachId);
}
