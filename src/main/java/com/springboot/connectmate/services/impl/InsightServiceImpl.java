package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.Insight.InsightDTO;
import com.springboot.connectmate.enums.InsightStatus;
import com.springboot.connectmate.models.Insight;
import com.springboot.connectmate.repositories.InsightRepository;
import com.springboot.connectmate.services.InsightService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
                    dto.setSummaryTemplate((String) result[3]);
                    dto.setSituationTemplate((String) result[4]);
                    dto.setActionsTemplate((String) result[5]);
                    dto.setThresholdBreachId((Long) result[6]);
                    dto.setConstructedDescription((String)result[2] + (String)result[7]);
                    return dto;
                })
                .collect(Collectors.toList());
    }
    @Override
    public List<InsightDTO> getAllInsights() {
        return insightRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private InsightDTO convertToDTO(Insight insight) {
        InsightDTO dto = new InsightDTO();
        dto.setId(insight.getId());
        dto.setConstructedDescription(insight.getConstructedDescription());
        dto.setThresholdBreachId(insight.getThresholdBreach().getId());
        dto.setStatus(insight.getStatus());
        return dto;
    }
}
