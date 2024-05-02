package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.Metric.ConnectMetricDTO;
import com.springboot.connectmate.dtos.Metric.MetricDTO;
import com.springboot.connectmate.enums.MetricCategory;
import com.springboot.connectmate.dtos.Metric.MetricDescriptionDTO;
import com.springboot.connectmate.dtos.Metric.MetricThresholdsDTO;
import com.springboot.connectmate.exceptions.ResourceNotFoundException;
import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.repositories.MetricRepository;
import com.springboot.connectmate.services.MetricService;

import com.springboot.connectmate.dtos.Metric.MetricThresholdsDTO;

import jakarta.persistence.Tuple;
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
    public MetricDescriptionDTO getMetricDescriptionById(Long metricId) {
        // Get Metric Description by ID
        Metric metric = metricRepository.findById(metricId).orElseThrow(() -> new ResourceNotFoundException("Metric", "id", metricId));
        MetricDescriptionDTO metricDescription = mapper.map(metric, MetricDescriptionDTO.class);
        // description is not part of the entity, therefore
        metricDescription.setDescription(metric.getCode().getDescription());
        return metricDescription;
    }

    @Override
    @Transactional
    public List<MetricDTO> getContactCenterMetrics() {
        // Get the data from a Store Procedure (SP) form our database server
        List<Object[]> results = metricRepository.getContactCenterMetrics();

        // Deserialization
        return results.stream()
                .map(result -> {
                    MetricDTO dto = new MetricDTO();
                    dto.setCode(MetricCategory.valueOf((String) result[0]));
                    dto.setName((String) result[1]);
                    dto.setValue((BigDecimal) result[2]);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MetricThresholdsDTO updateMetricThresholds(Long metricId, BigDecimal minimumThreshold, BigDecimal maximumThreshold) {
        Metric metric = metricRepository.findById(metricId).orElseThrow(() -> new ResourceNotFoundException("Metric", "id", metricId));

        metric.setMinimumThreshold(minimumThreshold);
        metric.setMaximumThreshold(maximumThreshold);
        metric = metricRepository.save(metric);

        MetricThresholdsDTO metricDTO = new MetricThresholdsDTO();
        metricDTO.setMetricId(metric.getId());
        metricDTO.setMinimumThreshold(metric.getMinimumThreshold());
        metricDTO.setMaximumThreshold(metric.getMaximumThreshold());
        return metricDTO;
    }

    @Override
    public List<ConnectMetricDTO> getAgentMetrics(Long agentId){
        List<Tuple> results = metricRepository.getAgentMetrics(agentId);
        return results.stream()
                .map(result -> {
                    ConnectMetricDTO dto = new ConnectMetricDTO();
                    dto.setId(result.get("id", Long.class));
                    dto.setMetric_info_code(String.valueOf(MetricCategory.valueOf(result.get("metric_info_code", String.class))));
                    dto.setValue(result.get("value", BigDecimal.class));
                    dto.setAgent_id(result.get("agent_id", Long.class));
                    dto.setQueue_id(result.get("queue_id", Long.class));
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
