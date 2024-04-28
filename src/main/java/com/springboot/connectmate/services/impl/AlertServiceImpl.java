package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.AlertDTO;
import com.springboot.connectmate.model.Alert;
import com.springboot.connectmate.repository.AlertRepository;
import com.springboot.connectmate.services.AlertService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private ModelMapper modelMapper;  // Ensure you have a ModelMapper bean configured in your Spring context

    @Override
    public List<AlertDTO> getAllAlerts() {
        return alertRepository.findAll().stream().map(alert -> modelMapper.map(alert, AlertDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AlertDTO getAlertById(Long id) {
        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found with id " + id));
        return modelMapper.map(alert, AlertDTO.class);
    }

    @Override
    public AlertDTO createAlert(AlertDTO alertDTO) {
        Alert alert = modelMapper.map(alertDTO, Alert.class);
        Alert savedAlert = alertRepository.save(alert);
        return modelMapper.map(savedAlert, AlertDTO.class);
    }

    @Override
    public AlertDTO updateAlert(AlertDTO alertDTO) {
        Alert existingAlert = alertRepository.findById(alertDTO.getId())
                .orElseThrow(() -> new RuntimeException("Alert not found with id " + alertDTO.getId()));
        modelMapper.map(alertDTO, existingAlert);
        Alert updatedAlert = alertRepository.save(existingAlert);
        return modelMapper.map(updatedAlert, AlertDTO.class);
    }

    @Override
    public void deleteAlert(Long id) {
        if (!alertRepository.existsById(id)) {
            throw new RuntimeException("Alert not found with id " + id);
        }
        alertRepository.deleteById(id);
    }
    }

    /*

1. **getAllAlerts**: Busca todas las entidades `Alert` en la base de datos, las mapea a `AlertDTO` usando `ModelMapper`, y devuelve una lista de ellas.
2. **getAlertById**: Busca una alerta específica por ID. Si no la encuentra, lanza una excepción. Si la encuentra, la mapea a `AlertDTO`.
3. **createAlert**: Convierte el `AlertDTO` recibido en una entidad `Alert`, la guarda en la base de datos y devuelve el `AlertDTO` de la alerta guardada.
4. **updateAlert**: Verifica primero si la alerta existe, mapea los cambios del `AlertDTO` recibido a la entidad existente, la guarda y devuelve el `AlertDTO` actualizado.
5. **deleteAlert**: Verifica si la alerta existe por ID y, si existe, la elimina.

 */