package com.springboot.connectmate.services;


import com.springboot.connectmate.dtos.AmazonConnect.InsightDTO;
import com.springboot.connectmate.dtos.AmazonConnect.ThresholdBreachInsightDTO;

import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.models.ThresholdBreachInsight;

public interface ThresholdBreachInsightService {
    void updateInsightStatus(Long id, Status newStatus);

    //ThresholdBreachInsight generateAndSaveInsight(ThresholdBreachInsightDTO dto, InsightDTO insight);

}
