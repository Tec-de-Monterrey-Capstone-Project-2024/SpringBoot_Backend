package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.AmazonConnect.*;
import com.springboot.connectmate.dtos.AmazonConnect.ConnectUserDataDTO;

import java.util.List;

public interface AmazonConnectService {
    List<ConnectInstanceDTO> listConnectInstances();
    List<ConnectQueueDTO> listQueues(String instanceId);
    List<ConnectAgentDTO> listAgents(String instanceId);
    List<ConnectUserDTO> listUsers(String instanceId);
    List<ConnectRoutingProfileDTO> listRoutingProfiles(String instanceId);

    List<String> getHistoricalMetrics(String instanceId, String queueId);
    List<String> getHistoricalMetricsV2(String instanceId, String queueId);
    List<String> getCurrentMetrics(String instanceId);


    String getTests(String instanceId);
    String listTests(String instanceId);

    List<ConnectUserDataDTO> getCurrentData(String instanceId);

}
