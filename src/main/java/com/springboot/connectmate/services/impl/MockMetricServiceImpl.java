package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.MockMetric.MockMetricDTO;
import com.springboot.connectmate.services.AmazonConnectService;
import com.springboot.connectmate.services.MockMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MockMetricServiceImpl implements MockMetricService {

    private final AmazonConnectService amazonConnectService;

    @Autowired
    public MockMetricServiceImpl(AmazonConnectService amazonConnectService) {
        this.amazonConnectService = amazonConnectService;
    }

    @Override
    public List<MockMetricDTO> getAllInstanceMetrics() {
        String connectMateInstance = "7c78bd60-4a9f-40e5-b461-b7a0dfaad848";
        return null;
    }

    @Override
    public List<MockMetricDTO> getAllQueueMetrics() {
        String connectMateInstance = "7c78bd60-4a9f-40e5-b461-b7a0dfaad848";

        return null;
    }

    @Override
    public List<MockMetricDTO> getAllAgentMetrics() {
        String connectMateInstance = "7c78bd60-4a9f-40e5-b461-b7a0dfaad848";
        return null;
    }
}
