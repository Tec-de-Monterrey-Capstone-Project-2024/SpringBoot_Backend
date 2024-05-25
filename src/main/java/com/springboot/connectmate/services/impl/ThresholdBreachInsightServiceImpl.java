package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.repositories.ThresholdBreachInsightRepository;
import com.springboot.connectmate.services.ThresholdBreachInsightService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
