package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.Alert.AlertDTO;
import com.springboot.connectmate.model.Alert;
import com.springboot.connectmate.repositories.AlertRepository;
import com.springboot.connectmate.services.AlertService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AlertServiceImpl(AlertRepository alertRepository, ModelMapper modelMapper) {
        this.alertRepository = alertRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AlertDTO> getAllAlerts() {
        return alertRepository.findAll().stream()
               .map(this::convertToDTO)
               .collect(Collectors.toList());
    }

    private AlertDTO convertToDTO(Alert alert) {
        return modelMapper.map(alert, AlertDTO.class);
    }

    private Alert convertToEntity(AlertDTO alertDTO) {
        return modelMapper.map(alertDTO, Alert.class);
    }

    @Override
    public AlertDTO getAlertById(Long alertId) {
        Alert alert = alertRepository.findById(alertId)
                .orElseThrow(() -> new IllegalArgumentException("Alert not found with ID: " + alertId));
        return convertToDTO(alert);
    }

    @Override
    public AlertDTO createAlert(AlertDTO alertDTO) {
        Alert alert = convertToEntity(alertDTO);
        alert = alertRepository.save(alert);
        return convertToDTO(alert);
    }

    @Override
    public AlertDTO updateAlert(AlertDTO alertDTO) {
        Alert existingAlert = alertRepository.findById(alertDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Alert not found with ID: " + alertDTO.getId()));
        modelMapper.map(alertDTO, existingAlert);
        alertRepository.save(existingAlert);
        return convertToDTO(existingAlert);
    }

    @Override
    public void deleteAlert(Long alertId) {
        if (!alertRepository.existsById(alertId)) {
            throw new IllegalArgumentException("Alert not found with ID: " + alertId);
        }
        alertRepository.deleteById(alertId);
    }
}
