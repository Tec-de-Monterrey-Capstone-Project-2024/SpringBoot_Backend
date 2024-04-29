package com.springboot.connectmate.services;

import com.amazonaws.services.connect.model.CurrentMetric;

import java.util.List;

public interface AmazonConnectService {
    List<String> listConnectInstances();
    List<String> listQueues(String instanceId);
    List<String> listAgents(String instanceId);
    }
