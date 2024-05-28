package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import com.springboot.connectmate.repositories.ThresholdBreachInsightRepository;
import com.springboot.connectmate.services.ThresholdBreachInsightService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public ThresholdBreachInsight updateStatus(Long id, Status status) {
        Optional<ThresholdBreachInsight> optionalInsight = thresholdBreachInsightRepository.findById(id);
        if (optionalInsight.isPresent()) {
            ThresholdBreachInsight insight = optionalInsight.get();
            insight.setStatus(status);
            return thresholdBreachInsightRepository.save(insight);
        } else {
            throw new RuntimeException("ThresholdBreachInsight not found with id: " + id);
        }
    }
}
