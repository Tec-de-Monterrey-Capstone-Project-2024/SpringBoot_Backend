package com.springboot.connectmate.services.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ThresholdCheckServiceImpl {
    // Need connect metrics and threshold repositories for getting the metrics and thresholds
    @Scheduled(fixedRate = 10000, initialDelay = 10000) // Run every 10 seconds with an initial delay of 10 seconds
    public void checkThresholdBreaches() {
        System.out.println("Checking for threshold breaches...");
    }
}
