package com.springboot.connectmate.services.impl;


import com.springboot.connectmate.repositories.MetricRepository;
import com.springboot.connectmate.services.MetricService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MetricServiceImpl implements MetricService {

    private final MetricRepository metricRepository;
    private final ModelMapper mapper;

    @Autowired
    public MetricServiceImpl(MetricRepository metricRepository, ModelMapper mapper) {
        this.metricRepository = metricRepository;
        this.mapper = mapper;
    }


}
