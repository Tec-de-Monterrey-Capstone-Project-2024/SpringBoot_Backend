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
    public AlertDTO getAlertById(Long alertId) {
        // gets the threshold breach by id
        ThresholdBreach thresholdBreach = thresholdBreachRepository.findById(alertId).orElseThrow(() -> new ResourceNotFoundException("Alert", "id", alertId));
        // get the metric by id
        MetricDescriptionDTO metricDescription = metricService.getMetricDescriptionById(thresholdBreach.getMetric().getId());
        // map the threshold breach to the alert dto
        AlertDTO alert = mapper.map(thresholdBreach, AlertDTO.class);
        // set the metric description
        alert.setMetric(metricDescription);

        return alert;
    }
    @Override
    public List<AlertDTO> getAllThresholdBreachAlerts() {
        List<ThresholdBreach> breaches = thresholdBreachRepository.findAll(); // Assuming this fetches all breaches
        return breaches.stream()
                       .map(breach -> mapper.map(breach, AlertDTO.class))
                       .collect(Collectors.toList());
    }
}