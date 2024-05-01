package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Alert.AlertDTO;
import com.springboot.connectmate.models.ThresholdBreach;

import java.util.List;

public interface AlertService {
    AlertDTO getAlertById(Long alertId);
    List<ThresholdBreach> getAllThresholdBreachAlerts(); // Method to fetch all alerts for threshold breaches

}