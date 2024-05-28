package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.AmazonConnect.InsightDTO;
import com.springboot.connectmate.dtos.AmazonConnect.ThresholdBreachInsightDTO;
import com.springboot.connectmate.enums.*;
import com.springboot.connectmate.exceptions.ResourceNotFoundException;
import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import com.springboot.connectmate.repositories.ThresholdBreachInsightRepository;
import com.springboot.connectmate.services.ThresholdBreachInsightService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ThresholdBreachInsightServiceImpl implements ThresholdBreachInsightService {

    private final ThresholdBreachInsightRepository thresholdBreachInsightRepository;
    private final ModelMapper mapper;

    @Autowired
    public ThresholdBreachInsightServiceImpl(ThresholdBreachInsightRepository thresholdBreachInsightRepository,
                                             ModelMapper mapper
    ) {
        this.thresholdBreachInsightRepository = thresholdBreachInsightRepository;
        this.mapper = mapper;
    }


    @Transactional
    public void updateInsightStatus(Long insightId, Status newStatus) {
        ThresholdBreachInsight insight = thresholdBreachInsightRepository.findById(insightId)
                .orElseThrow(() -> new ResourceNotFoundException("ThresholdBreachInsight", "id", insightId));
        insight.setStatus(newStatus);
        thresholdBreachInsightRepository.save(insight);
    }


    @Override
    @Transactional
    public ThresholdBreachInsight generateAndSaveInsight(ThresholdBreachInsightDTO dto, InsightDTO insight) {

        Metric metric = new Metric();
        metric.setCode(dto.getMetricCode());

        ThresholdBreachInsight thresholdBreachInsight = new ThresholdBreachInsight();
        thresholdBreachInsight.setMetricCode(metric);
        thresholdBreachInsight.setConnectItemId(dto.getConnectItemId());
        thresholdBreachInsight.setConnectItemType(dto.getConnectItemType());
        thresholdBreachInsight.setValue(dto.getValue());
        thresholdBreachInsight.setOccurredAt(dto.getOccurredAt() != null ? dto.getOccurredAt() : LocalDateTime.now());
        thresholdBreachInsight.setStatus(dto.getStatus());
        thresholdBreachInsight.setInsightName(dto.getInsightName());
        thresholdBreachInsight.setInsightSummary(dto.getInsightSummary());
        thresholdBreachInsight.setInsightDescription(dto.getInsightDescription());
        thresholdBreachInsight.setInsightActions(dto.getInsightActions());
        thresholdBreachInsight.setInsightCategory(dto.getInsightCategory());
        thresholdBreachInsight.setInsightSeverity(dto.getInsightSeverity());
        thresholdBreachInsight.setInsightRootCause(dto.getInsightRootCause());
        thresholdBreachInsight.setInsightImpact(dto.getInsightImpact());
        thresholdBreachInsight.setInsightPrevention(dto.getInsightPrevention());

        return thresholdBreachInsightRepository.save(thresholdBreachInsight);
    }
}
