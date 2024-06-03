package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.ThresholdBreachInsight.*;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.exceptions.ResourceNotFoundException;
import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import com.springboot.connectmate.repositories.ThresholdBreachInsightRepository;
import com.springboot.connectmate.repositories.MetricRepository;
import com.springboot.connectmate.services.ThresholdBreachInsightService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ThresholdBreachInsightServiceImpl implements ThresholdBreachInsightService {

    private final ThresholdBreachInsightRepository thresholdBreachInsightRepository;

    @Autowired
    private MetricRepository metricRepository;

    private final ModelMapper mapper;

    @Autowired
    public ThresholdBreachInsightServiceImpl(ThresholdBreachInsightRepository thresholdBreachInsightRepository,
                                             ModelMapper mapper
    ) {
        this.thresholdBreachInsightRepository = thresholdBreachInsightRepository;
        this.mapper = mapper;
    }


    @Override
    public Optional<ThresholdBreachInsight> getInsightByMetricCodeAndConnectItemId(Metric metric, String connectItemId) {
        return thresholdBreachInsightRepository.findByMetricCodeAndConnectItemId(metric, connectItemId);
    }

    @Override
    public void deleteInsight(ThresholdBreachInsight insight) {
        thresholdBreachInsightRepository.delete(insight);
    }

    @Override
    public void saveInsight(
            Metric metric,
            ThresholdBreachFieldsDTO thresholdBreachData,
            InsightFieldsDTO insightData) {

        // Map the threshold breach data
        ThresholdBreachInsight thresholdBreachInsight = mapper.map(thresholdBreachData, ThresholdBreachInsight.class);
        // Map the insight data
        mapper.map(insightData, thresholdBreachInsight);
        // Link the insight to the metric (Foreign Key)
        thresholdBreachInsight.setMetricCode(metric);

        thresholdBreachInsightRepository.save(thresholdBreachInsight);
    }

    @Override
    public List<ThresholdBreachInsightGenericDTO> getAllInsights() {
        List<ThresholdBreachInsight> insights = thresholdBreachInsightRepository.findAll();
        return insights.stream()
                .map(insight -> mapper.map(insight, ThresholdBreachInsightGenericDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ThresholdBreachInsightGenericDTO> getInsightsByStatus(Status status) {
        List<ThresholdBreachInsight> insights = thresholdBreachInsightRepository.findByStatus(status);
        return insights.stream()
                .map(insight -> mapper.map(insight, ThresholdBreachInsightGenericDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ThresholdBreachInsightGenericDTO> getInsightsByConnectItemId(String connectItemId) {
        List<ThresholdBreachInsight> insights = thresholdBreachInsightRepository.findByConnectItemId(connectItemId);
        return insights.stream()
                .map(insight -> mapper.map(insight, ThresholdBreachInsightGenericDTO.class))
                .collect(Collectors.toList());
    }

    @Override  
    public List<ThresholdBreachInsightGenericDTO> getInsightsByItemType(ConnectMetricType connectItemType) {
        List<ThresholdBreachInsight> insights = thresholdBreachInsightRepository.findByConnectItemType(connectItemType);
        return insights.stream()
                .map(insight -> mapper.map(insight, ThresholdBreachInsightGenericDTO.class))
                .collect(Collectors.toList());
    }
  
    @Override
    public ThresholdBreachInsightDetailDTO getInsightById(Long id) {
        ThresholdBreachInsight insight = thresholdBreachInsightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ThresholdBreachInsight", "id", id));
        return mapper.map(insight, ThresholdBreachInsightDetailDTO.class);
    }
  
    @Override
    public Map<Status, List<ThresholdBreachInsightGenericDTO>> getInsightsByStatus() {
        return Arrays.stream(Status.values())
                .collect(Collectors.toMap(
                        status -> status,
                        this::getInsightsByStatus
                ));
    }

    @Override
    public String updateStatus(Long id, Status status) {
        ThresholdBreachInsight insight = thresholdBreachInsightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ThresholdBreachInsight", "id", id));
        insight.setStatus(status);
        thresholdBreachInsightRepository.save(insight);
        return "Status updated successfully";
    }


    private InsightAlertDTO mapToInsightAlertDTO(ThresholdBreachInsight insight) {
        InsightAlertDTO alert = new InsightAlertDTO();
        alert.setId(insight.getId());
        alert.setMetricName(insight.getMetricCode().getCode().getName());
        alert.setConnectItemType(insight.getConnectItemType());
        alert.setInsightCategory(insight.getInsightCategory());
        alert.setOccurredAt(insight.getOccurredAt());
        return alert;
    }
    @Override
    public List<InsightAlertDTO> getAlerts() {
        List<ThresholdBreachInsight> insights = thresholdBreachInsightRepository.findAll();

        return  insights.stream()
                .map(this::mapToInsightAlertDTO)
                .collect(Collectors.toList());
    }
    
}
