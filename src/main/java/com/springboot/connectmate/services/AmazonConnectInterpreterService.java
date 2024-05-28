package com.springboot.connectmate.services;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

public interface AmazonConnectInterpreterService {
    Map<String, Map<String, Object>> getQueueUserCounts(String instanceId);
}
