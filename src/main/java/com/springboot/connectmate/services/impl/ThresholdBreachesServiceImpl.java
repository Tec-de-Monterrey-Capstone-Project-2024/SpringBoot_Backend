package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.repositories.ThresholdBreachesRepository;
import com.springboot.connectmate.services.ThresholdBreachesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThresholdBreachesServiceImpl implements ThresholdBreachesService {
    private final ModelMapper mapper;
    private final ThresholdBreachesRepository thresholdBreachesRepository;

    @Autowired
    public ThresholdBreachesServiceImpl(ThresholdBreachesRepository thresholdBreachesRepository,
                                        ModelMapper mapper
    ) {
        this.thresholdBreachesRepository = thresholdBreachesRepository;
        this.mapper = mapper;
    }
}
