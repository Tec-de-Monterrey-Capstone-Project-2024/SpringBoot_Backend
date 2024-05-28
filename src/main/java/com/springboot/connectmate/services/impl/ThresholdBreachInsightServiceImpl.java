package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.Insight.ThresholdBreachInsightDTO;
import com.springboot.connectmate.dtos.Insight.UpdateStatusDTO;
import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import com.springboot.connectmate.exceptions.ResourceNotFoundException;
import com.springboot.connectmate.repositories.ThresholdBreachInsightRepository;
import com.springboot.connectmate.services.ThresholdBreachInsightService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThresholdBreachInsightServiceImpl implements ThresholdBreachInsightService {

    private final ThresholdBreachInsightRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public ThresholdBreachInsightServiceImpl(ThresholdBreachInsightRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ThresholdBreachInsightDTO> getInsightsByStatus(Status status) {
        List<ThresholdBreachInsight> insights = repository.findByStatus(status);
        return insights.stream()
                .map(insight -> mapper.map(insight, ThresholdBreachInsightDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ThresholdBreachInsightDTO> getInsightsByConnectItemId(String connectItemId) {
        List<ThresholdBreachInsight> insights = repository.findByConnectItemId(connectItemId);
        return insights.stream()
                .map(insight -> mapper.map(insight, ThresholdBreachInsightDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ThresholdBreachInsightDTO> getAllInsights() {
        List<ThresholdBreachInsight> insights = repository.findAll();
        return insights.stream()
                .map(insight -> mapper.map(insight, ThresholdBreachInsightDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ThresholdBreachInsightDTO> getInsightsByItemType(ConnectMetricType connectMetricType) {
        List<ThresholdBreachInsight> insights = repository.findByConnectItemType(connectMetricType);
        return insights.stream()
                .map(insight -> mapper.map(insight, ThresholdBreachInsightDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ThresholdBreachInsightDTO updateStatus(Long id, UpdateStatusDTO updateStatusDTO) {
        ThresholdBreachInsight insight = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ThresholdBreachInsight", "id", id));

        insight.setStatus( updateStatusDTO.getStatus() );
        ThresholdBreachInsight updatedInsight = repository.save(insight);

        return mapper.map(updatedInsight, ThresholdBreachInsightDTO.class);
    }
}

