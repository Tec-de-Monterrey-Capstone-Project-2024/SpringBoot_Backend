package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.Metric.MetricDTO;
import com.springboot.connectmate.enums.MetricCategory;
import com.springboot.connectmate.repositories.MetricRepository;
import com.springboot.connectmate.services.MetricService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional
    public List<MetricDTO> getContactCenterMetrics(){
        /* Get the data from a Store Procedure (SP) form our database server */
        List<Object[]> results = metricRepository.getContactCenterMetrics();

        /* Deserialization */
        return results.stream()
                .map(result -> {
                    MetricDTO dto = new MetricDTO();
                    dto.setCode((MetricCategory) result[0]);
                    dto.setName((String) result[1]);
                    dto.setValue((BigDecimal) result[2]);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
