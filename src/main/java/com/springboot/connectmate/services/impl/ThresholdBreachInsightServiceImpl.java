package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.Metric.ThresholdBreachInsightDTO;
import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.enums.ConnectItemType;
import com.springboot.connectmate.models.ThresholdBreachInsight;
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

    @Autowired
    public ThresholdBreachInsightServiceImpl(ThresholdBreachInsightRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
    }

    @Override
    public List<ThresholdBreachInsightDTO> getInsightsByStatus(Status status) {
        List<ThresholdBreachInsight> insights = repository.findByStatus(status);
        return insights.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ThresholdBreachInsightDTO> getInsightsByConnectItemId(String connectItemId) {
        List<ThresholdBreachInsight> insights = repository.findByConnectItemId(connectItemId);
        return insights.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ThresholdBreachInsightDTO> getAllInsights(){
        List<ThresholdBreachInsight> insights = repository.findAll();
        return insights.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ThresholdBreachInsightDTO> getInsightsByItemType(ConnectItemType connectItemType) {
        List<ThresholdBreachInsight> insights = repository.findByConnectItemType(connectItemType);
        return insights.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    private ThresholdBreachInsightDTO convertToDTO(ThresholdBreachInsight insight) {
        ThresholdBreachInsightDTO dto = new ThresholdBreachInsightDTO();
        dto.setId(insight.getId());

        if (insight.getMetricCode() != null) {
            dto.setMetricCode(String.valueOf(insight.getMetricCode().getCode())); // Converting enum to String
        }

        dto.setConnectItemId(insight.getConnectItemId());
        dto.setConnectItemType(insight.getConnectItemType().name());
        dto.setValue(insight.getValue());
        dto.setOccurredAt(insight.getOccurredAt().toString());
        dto.setStatus(insight.getStatus().name());
        dto.setInsightName(insight.getInsightName());
        dto.setInsightSummary(insight.getInsightSummary());
        dto.setInsightDescription(insight.getInsightDescription());
        dto.setInsightActions(insight.getInsightActions());
        dto.setInsightCategory(insight.getInsightCategory().name());
        dto.setInsightSeverity(insight.getInsightSeverity().name());
        dto.setInsightRootCause(insight.getInsightRootCause());
        dto.setInsightImpact(insight.getInsightImpact());
        dto.setInsightPrevention(insight.getInsightPrevention());

        return dto;
    }
}

