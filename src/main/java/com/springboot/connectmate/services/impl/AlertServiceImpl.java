package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.Alert.AlertDTO;
import com.springboot.connectmate.dtos.Metric.MetricDescriptionDTO;
import com.springboot.connectmate.models.Alert;
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
        dto.setMetric(ConversionUtil(alert.getMetric()));        
        dto.setValue(alert.getValue());
        dto.setBreachCategory(alert.getBreachCategory());
        dto.setOccurredAt(alert.getOccurredAt());
        return dto;
    }

    private MetricDescriptionDTO ConversionUtil(Object metric) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ConversionUtil'");
    }

    @Override
    public AlertDTO getAlertById(Long alertId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAlertById'");
    }

    @Override
    public AlertDTO updateAlert(AlertDTO alertDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAlert'");
    }

    @Override
    public AlertDTO createAlert(AlertDTO alertDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAlert'");
    }

    @Override
    public void deleteAlert(Long alertId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAlert'");
    }
}
