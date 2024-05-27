package com.springboot.connectmate.services;

import com.amazonaws.services.connect.model.*;

import java.util.List;

public interface AmazonConnectService {
    // General Services
    List<InstanceSummary> listConnectInstances();
    Instance getConnectInstance(String instanceId);

    // User Services
    List<UserSummary> listUsers(String instanceId);
    User getUserDescription(String instanceId, String userId);
    List<UserData> getCurrentUserData(String instanceId);
    List<AgentStatusSummary> listAgentsStatuses(String instanceId);
    List<RoutingProfileSummary> listRoutingProfiles(String instanceId);

    // Queue Services
    List<QueueSummary> listQueues(String instanceId);
    Queue describeQueue(String instanceId, String queueId);

    // Metric Services
    List<String> getHistoricalMetrics(String instanceId, String queueId);
    List<String> getHistoricalMetricsV2(String instanceId, String queueId);
    List<String> getCurrentMetrics(String instanceId);
}
