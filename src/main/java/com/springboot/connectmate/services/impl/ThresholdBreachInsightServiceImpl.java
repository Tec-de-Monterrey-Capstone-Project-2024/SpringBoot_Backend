package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.ThresholdBreachInsight.InsightDTO;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachInsightDetailDTO;
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
import java.time.LocalDateTime;
import java.util.List;
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
    @Transactional
    public ThresholdBreachInsight generateAndSaveInsight(ThresholdBreachInsightDetailDTO dto, InsightDTO insight) {
        Optional<Metric> metricOpt = metricRepository.findById(dto.getMetricCode());
        if (!metricOpt.isPresent()) {
            throw new ResourceNotFoundException("Metric not found with id: " + dto.getMetricCode());
        }
        Metric metric = metricOpt.get();

        ThresholdBreachInsight thresholdBreachInsight = mapper.map(dto, ThresholdBreachInsight.class);
        thresholdBreachInsight.setMetricCode(metric);
        thresholdBreachInsight.setOccurredAt(dto.getOccurredAt() != null ? LocalDateTime.parse(dto.getOccurredAt()) : LocalDateTime.now());
        ThresholdBreachInsight savedInsight = thresholdBreachInsightRepository.save(thresholdBreachInsight);

        return savedInsight;
    }

    @Override
    public ThresholdBreachInsight getInsightByMetricCodeAndConnectItemId(Metric metric, String connectItemId) {
        return thresholdBreachInsightRepository.findByMetricCodeAndConnectItemId(metric, connectItemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "ThresholdBreachInsight",
                        metric.getCode().toString(),
                        connectItemId));
    }

    @Override
    public List<ThresholdBreachInsightDetailDTO> getAllInsights() {
        List<ThresholdBreachInsight> insights = thresholdBreachInsightRepository.findAll();
        return insights.stream()
                .map(insight -> mapper.map(insight, ThresholdBreachInsightDetailDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ThresholdBreachInsightDetailDTO> getInsightsByStatus(Status status) {
        List<ThresholdBreachInsight> insights = thresholdBreachInsightRepository.findByStatus(status);
        return insights.stream()
                .map(insight -> mapper.map(insight, ThresholdBreachInsightDetailDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ThresholdBreachInsightDetailDTO> getInsightsByConnectItemId(String connectItemId) {
        List<ThresholdBreachInsight> insights = thresholdBreachInsightRepository.findByConnectItemId(connectItemId);
        return insights.stream()
                .map(insight -> mapper.map(insight, ThresholdBreachInsightDetailDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ThresholdBreachInsightDetailDTO> getInsightsByItemType(ConnectMetricType connectItemType) {
        List<ThresholdBreachInsight> insights = thresholdBreachInsightRepository.findByConnectItemType(connectItemType);
        return insights.stream()
                .map(insight -> mapper.map(insight, ThresholdBreachInsightDetailDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public String updateStatus(Long id, Status status) {
        ThresholdBreachInsight insight = thresholdBreachInsightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ThresholdBreachInsight", "id", id));
        insight.setStatus(status);
        thresholdBreachInsightRepository.save(insight);
        return "Status updated successfully";
    }
}
