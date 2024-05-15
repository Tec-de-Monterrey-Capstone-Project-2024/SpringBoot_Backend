package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.repositories.ThresholdBreachesRepository;
import com.springboot.connectmate.services.AlertService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertServiceImpl implements AlertService {

    private final ModelMapper mapper;
    private final ThresholdBreachesRepository thresholdBreachesRepository;

    @Autowired
    public AlertServiceImpl(ThresholdBreachesRepository thresholdBreachesRepository,
                            ModelMapper mapper
    ) {
        this.thresholdBreachesRepository = thresholdBreachesRepository;
        this.mapper = mapper;
    }
}