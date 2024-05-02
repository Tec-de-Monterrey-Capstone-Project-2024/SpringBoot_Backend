package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.Alert.AlertDTO;
import com.springboot.connectmate.dtos.Metric.MetricDescriptionDTO;
import com.springboot.connectmate.exceptions.ResourceNotFoundException;
import com.springboot.connectmate.models.ThresholdBreach;
import com.springboot.connectmate.repositories.ThresholdBreachRepository;
import com.springboot.connectmate.services.AlertService;
import com.springboot.connectmate.services.MetricService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertServiceImpl implements AlertService {

    private final ModelMapper mapper;
    private final ThresholdBreachRepository thresholdBreachRepository;
    private final MetricService metricService;

    @Autowired
    public AlertServiceImpl(ThresholdBreachRepository thresholdBreachRepository,
                            MetricService metricService,
                            ModelMapper mapper
    ) {
        this.thresholdBreachRepository = thresholdBreachRepository;
        this.metricService = metricService;
        this.mapper = mapper;
    }

    @Override
    public List<AlertDTO> getAllAlerts() {
        List<ThresholdBreach> breaches = thresholdBreachRepository.findAll();
        return breaches.stream().map(breach -> {
            // Get the Metric Description DTO for each breach
            MetricDescriptionDTO metricDescription = metricService.getMetricDescriptionById(breach.getMetric().getId());

            // Return the constructed AlertDTO
            return new AlertDTO(
                    breach.getId(),
                    metricDescription,
                    breach.getValue(),
                    breach.getBreachCategory(),
                    breach.getOccurredAt()
            );
        }).collect(Collectors.toList());

    }

    @Override
    public AlertDTO getAlertById(Long alertId) {
        // gets the threshold breach by id
        ThresholdBreach thresholdBreach = thresholdBreachRepository.findById(alertId).orElseThrow(() -> new ResourceNotFoundException("Alert", "id", alertId));
        // get the metric id
        Long metric_id = thresholdBreach.getMetric().getId();
        // Use Service to get the DTO
        MetricDescriptionDTO metricDescription = metricService.getMetricDescriptionById(metric_id);
        // map the threshold breach to the alert dto
        AlertDTO alert = mapper.map(thresholdBreach, AlertDTO.class);
        // set the metric description
        alert.setMetric(metricDescription);
        return alert;
    }
}
