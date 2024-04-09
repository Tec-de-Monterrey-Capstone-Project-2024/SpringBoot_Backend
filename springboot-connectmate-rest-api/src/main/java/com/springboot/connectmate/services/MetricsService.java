package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.GeneralMetricsDTO;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {

    public GeneralMetricsDTO updateMetrics(long id, GeneralMetricsDTO metricsDTO) {
        GeneralMetricsDTO existingMetrics = findMetricsById(id);

        if (existingMetrics == null) {
            return null; 
        }

        existingMetrics.setName(metricsDTO.getName());
        existingMetrics.setValue(metricsDTO.getValue());

        return existingMetrics;
    }

    private GeneralMetricsDTO findMetricsById(long id) {
        return null;
    }
}
