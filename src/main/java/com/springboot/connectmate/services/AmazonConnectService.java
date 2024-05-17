package com.springboot.connectmate.services;

import com.amazonaws.services.connect.model.*;
import com.springboot.connectmate.dtos.AmazonConnect.*;
import com.springboot.connectmate.dtos.AmazonConnect.ConnectUserDataDTO;

import java.util.List;

public interface AmazonConnectService {
    List<InstanceSummary> listConnectInstances();
    List<QueueSummary> listQueues(String instanceId);
    List<AgentStatusSummary> listAgents(String instanceId);
    List<UserSummary> listUsers(String instanceId);
    List<RoutingProfileSummary> listRoutingProfiles(String instanceId);

    List<String> getHistoricalMetrics(String instanceId, String queueId);
    List<String> getHistoricalMetricsV2(String instanceId, String queueId);
    List<String> getCurrentMetrics(String instanceId);

    User getUserDescription(String instanceId, String userId);


    String getTests(String instanceId);
    String listTests(String instanceId);

    List<ConnectUserDataDTO> getCurrentData(String instanceId);

}
