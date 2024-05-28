package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.AmazonConnect.InsightDTO;
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


    /*
    @Transactional
    public ThresholdBreachInsight generateAndSaveInsight(InsightDTO insight, Double metricValue, ConnectMetricType metricType, String typeId) {

        Metric metric = new Metric();
        ThresholdBreachInsight thresholdBreachInsight = new ThresholdBreachInsight();
        thresholdBreachInsight.setMetricCode(metric);
        thresholdBreachInsight.setConnectItemId(typeId);
        thresholdBreachInsight.setConnectItemType(metricType);
        thresholdBreachInsight.setValue(metricValue);
        thresholdBreachInsight.setOccurredAt(LocalDateTime.now());
        thresholdBreachInsight.setStatus(Status.TO_DO);
        thresholdBreachInsight.setInsightName(insight.getInsightName());
        thresholdBreachInsight.setInsightSummary(insight.getInsightSummary());
        thresholdBreachInsight.setInsightDescription(insight.getInsightDescription());
        thresholdBreachInsight.setInsightActions(insight.getInsightActions());
        thresholdBreachInsight.setInsightCategory(InsightPerformance.valueOf(insight.getInsightCategory().toUpperCase()));
        thresholdBreachInsight.setInsightSeverity(InsightSeverity.LOW);
        thresholdBreachInsight.setInsightRootCause(insight.getInsightRootCause());
        thresholdBreachInsight.setInsightImpact(insight.getInsightImpact());
        thresholdBreachInsight.setInsightPrevention(insight.getInsightPrevention());

        return thresholdBreachInsightRepository.save(thresholdBreachInsight);
    }*/
}
