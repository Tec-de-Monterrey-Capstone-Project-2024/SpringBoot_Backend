package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Alert.AlertDTO;

public interface AlertService {
    AlertDTO getAlertById(Long alertId);
}
