package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.Insight.InsightDTO;
import com.springboot.connectmate.enums.InsightStatus;
import com.springboot.connectmate.models.Insight;
import com.springboot.connectmate.repositories.InsightRepository;
import com.springboot.connectmate.services.InsightService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InsightServiceImpl implements InsightService {

    private final ModelMapper mapper;
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

    @Override
    @Transactional
    public List<InsightDTO> getQueueInsights() {
        List<Object[]> results = insightRepository.getQueueInsights();
        return results.stream()
                .map(result -> {
                    InsightDTO dto = new InsightDTO();
                    dto.setId((Long) result[0]);
                    dto.setStatus(InsightStatus.valueOf((String) result[1]));
                    dto.setSummaryTemplate((String) result[2]);
                    dto.setSituationTemplate((String) result[3]);
                    dto.setActionsTemplate((String) result[4]);
                    dto.setThresholdBreachId((Long) result[5]);
                    dto.setConstructedDescription((String)result[6]);
                    return dto;
                })
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void updateInsightStatus(Long id, InsightStatus status) {
        Insight insight = insightRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Insight not found for id: " + id));
        insight.setStatus(status);
        insightRepository.save(insight);
    }
}
