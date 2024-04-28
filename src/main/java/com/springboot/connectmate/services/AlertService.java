package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.AlertDTO;
import java.util.List;

public interface AlertService {

    List<AlertDTO> getAllAlerts();

    AlertDTO getAlertById(Long id);

    AlertDTO createAlert(AlertDTO alertDTO);

    AlertDTO updateAlert(AlertDTO alertDTO);

    void deleteAlert(Long id);
}