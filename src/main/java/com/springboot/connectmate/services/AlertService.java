package com.springboot.connectmate.services;


import java.util.List;

import com.springboot.connectmate.dtos.Alert.AlertDTO;

public interface AlertService {
    AlertDTO getAlertById(Long alertId);

    AlertDTO updateAlert(AlertDTO alertDTO);

    List<AlertDTO> getAllAlerts();

    AlertDTO createAlert(AlertDTO alertDTO);

    void deleteAlert(Long alertId);
}

