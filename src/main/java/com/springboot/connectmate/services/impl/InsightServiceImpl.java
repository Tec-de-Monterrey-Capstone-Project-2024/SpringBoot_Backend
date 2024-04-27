package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.Insight.InsightDTO;
import com.springboot.connectmate.models.Insight;
import com.springboot.connectmate.repositories.InsightRepository;
import com.springboot.connectmate.services.InsightService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class InsightServiceImpl implements InsightService {

    private ModelMapper mapper;
    private final InsightRepository insightRepository;
    public InsightServiceImpl(InsightRepository insightRepository, ModelMapper mapper) {
        this.insightRepository = insightRepository;
        this.mapper = mapper;
    }

    @Override
    public InsightDTO getInsightByBreachId(Long breachId) {
        Insight insight = insightRepository.findByThresholdBreachId(breachId);
        return mapper.map(insight, InsightDTO.class);
    }

}
