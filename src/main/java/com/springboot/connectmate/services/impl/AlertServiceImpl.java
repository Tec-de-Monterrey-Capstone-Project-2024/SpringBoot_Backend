package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.Alert.AlertDTO;
import com.springboot.connectmate.model.Alert;
import com.springboot.connectmate.repositories.AlertRepository;
import com.springboot.connectmate.services.AlertService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Override
    public List<AlertDTO> getAllAlerts() {
        return alertRepository.findAll().stream()
               .map(this::convertToDTO)
               .collect(Collectors.toList());
    }

    private AlertDTO convertToDTO(Alert alert) {
        AlertDTO dto = new AlertDTO();
        dto.setId(alert.getId());
        dto.setMetric(ConversionUtil.convertToMetricDescriptionDTO(alert.getMetric()));
        dto.setValue(alert.getValue());
        dto.setBreachCategory(alert.getBreachCategory());
        dto.setOccurredAt(alert.getOccurredAt());
        return dto;
    }
}
