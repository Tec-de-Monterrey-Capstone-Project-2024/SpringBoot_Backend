package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Alert.AlertDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public interface AlertService {
    List<AlertDTO> getAllAlerts();
    AlertDTO getAlertById(Long alertId);
    AlertDTO updateAlert(AlertDTO alertDTO);
    AlertDTO createAlert(AlertDTO alertDTO);
    void deleteAlert(Long alertId);
}