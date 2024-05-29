package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.AmazonConnect.InsightDTO;
import com.springboot.connectmate.dtos.AmazonConnect.ThresholdBreachInsightDTO;
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

    @Override
    @Transactional
    public ThresholdBreachInsight generateAndSaveInsight(ThresholdBreachInsightDTO dto, InsightDTO insight) {

        Metric metric = new Metric();
        metric.setCode(dto.getMetricCode());
        ThresholdBreachInsight thresholdBreachInsight = mapper.map(dto, ThresholdBreachInsight.class);
            thresholdBreachInsight.setMetricCode(metric);
            thresholdBreachInsight.setOccurredAt(dto.getOccurredAt() != null ? dto.getOccurredAt() : LocalDateTime.now());
        ThresholdBreachInsight savedInsight = thresholdBreachInsightRepository.save(thresholdBreachInsight);

        return savedInsight;
    }
}
