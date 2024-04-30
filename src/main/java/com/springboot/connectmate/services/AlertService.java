package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Alert.AlertDTO;
import java.util.List;

public interface AlertService {
    AlertDTO getAlertById(Long alertId);
    List<AlertDTO> getAllThresholdBreachAlerts(); // Method to fetch all alerts for threshold breaches

}