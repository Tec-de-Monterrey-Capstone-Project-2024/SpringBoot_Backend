package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.Metric.MetricDescriptionDTO;
import com.springboot.connectmate.exceptions.ResourceNotFoundException;
import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.repositories.MetricRepository;
import com.springboot.connectmate.services.MetricService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricServiceImpl implements MetricService {

    private final ModelMapper mapper;
    private final MetricRepository metricRepository;

    @Autowired
    public MetricServiceImpl(MetricRepository metricRepository, ModelMapper mapper) {
        this.metricRepository = metricRepository;
        this.mapper = mapper;
    }

    @Override
    public MetricDescriptionDTO getMetricDescriptionById(Long metricId) {
        // Get Metric Description by ID
        Metric metric = metricRepository.findById(metricId).orElseThrow(() -> new ResourceNotFoundException("Metric", "id", metricId));
        MetricDescriptionDTO metricDescription = mapper.map(metric, MetricDescriptionDTO.class);
        // description is not part of the entity, therefore
        metricDescription.setDescription(metric.getCode().getDescription());
        return metricDescription;
    }
}
