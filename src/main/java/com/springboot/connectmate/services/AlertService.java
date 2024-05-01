package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Alert.AlertDTO;

import java.util.List;

public interface AlertService {
    List<AlertDTO> getAllAlerts();
    AlertDTO getAlertById(Long alertId);
}
