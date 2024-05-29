package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.Insight.ThresholdBreachInsightDTO;
import com.springboot.connectmate.dtos.Insight.UpdateStatusDTO;
import com.springboot.connectmate.enums.ConnectMetricCode;
import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import com.springboot.connectmate.exceptions.ResourceNotFoundException;
import com.springboot.connectmate.repositories.ThresholdBreachInsightRepository;
import com.springboot.connectmate.services.ThresholdBreachInsightService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThresholdBreachInsightServiceImpl implements ThresholdBreachInsightService {

    private final ThresholdBreachInsightRepository thresholdBreachInsightRepository;
    private final ModelMapper mapper;
    Logger logger = LoggerFactory.getLogger(ThresholdBreachInsightServiceImpl.class);

    @Autowired
    public ThresholdBreachInsightServiceImpl(ThresholdBreachInsightRepository thresholdBreachInsightRepository,
                                             ModelMapper mapper) {
        this.thresholdBreachInsightRepository = thresholdBreachInsightRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ThresholdBreachInsightDTO> getInsightsByStatus(Status status) {
        List<ThresholdBreachInsight> insights = thresholdBreachInsightRepository.findByStatus(status);
        return insights.stream()
                .map(insight -> mapper.map(insight, ThresholdBreachInsightDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ThresholdBreachInsightDTO> getInsightsByConnectItemId(String connectItemId) {
        List<ThresholdBreachInsight> insights = thresholdBreachInsightRepository.findByConnectItemId(connectItemId);
        return insights.stream()
                .map(insight -> mapper.map(insight, ThresholdBreachInsightDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ThresholdBreachInsight getInsightByMetricCodeAndConnectItemId(ConnectMetricCode metricCode, String connectItemId) {
        return null;
    }

    @Override
    public List<ThresholdBreachInsightDTO> getAllInsights() {
        List<ThresholdBreachInsight> insights = thresholdBreachInsightRepository.findAll();

        return insights.stream()
                .map(insight -> {
                    System.out.println("Mapping ThresholdBreachInsight to ThresholdBreachInsightDTO");
                    System.out.println("ThresholdBreachInsight: " + insight);
                    System.out.println("ThresholdBreachInsightDTO: " + mapper.map(insight, ThresholdBreachInsightDTO.class));
                    ThresholdBreachInsightDTO dto = mapper.map(insight, ThresholdBreachInsightDTO.class);
                    logger.info("Mapping ThresholdBreachInsight to ThresholdBreachInsightDTO");
                    logger.info("ThresholdBreachInsight: {}", insight);
                    logger.info("ThresholdBreachInsightDTO: {}", dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ThresholdBreachInsightDTO> getInsightsByItemType(ConnectMetricType connectMetricType) {
        List<ThresholdBreachInsight> insights = thresholdBreachInsightRepository.findByConnectItemType(connectMetricType);
        return insights.stream()
                .map(insight -> mapper.map(insight, ThresholdBreachInsightDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public String updateStatus(Long id, UpdateStatusDTO updateStatusDTO) {
        ThresholdBreachInsight insight = thresholdBreachInsightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ThresholdBreachInsight", "id", id));

        insight.setStatus( updateStatusDTO.getStatus() );
        ThresholdBreachInsight updatedInsight = thresholdBreachInsightRepository.save(insight);

        return "Status updated successfully";
    }
}

