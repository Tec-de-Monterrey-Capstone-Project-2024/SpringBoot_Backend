package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.repositories.InsightRepository;
import com.springboot.connectmate.repositories.ThresholdBreachRepository;
import com.springboot.connectmate.services.AlertService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertServiceImpl implements AlertService {

    private final ModelMapper mapper;
    private final ThresholdBreachRepository thresholdBreachRepository;

    @Autowired
    public AlertServiceImpl(ThresholdBreachRepository thresholdBreachRepository, ModelMapper mapper) {
        this.thresholdBreachRepository = thresholdBreachRepository;
        this.mapper = mapper;
    }
}
