package com.springboot.connectmate.services;

import com.amazonaws.services.connect.model.*;
import com.springboot.connectmate.dtos.AmazonConnect.UserRoleDTO;

import java.util.List;
import java.util.Map;

public interface AmazonConnectService {
    // General Services
    List<InstanceSummary> listConnectInstances();
    Instance getConnectInstance(String instanceId);

    // User Services
    List<UserSummary> listUsers(String instanceId);
    User getUserDescription(String instanceId, String userId);
    List<ConnectCurrentUserDataDTO> getCurrentUserData(String instanceId);
    List<AgentStatusSummary> listAgentsStatuses(String instanceId);
    List<RoutingProfileSummary> listRoutingProfiles(String instanceId);
    List<UserRoleDTO> getUserSecurityProfileIds(String instanceId, String userId);

    // Queue Services
    List<ConnectQueueDTO> listQueues(String instanceId);
    Queue describeQueue(String instanceId, String queueId);

    // Metric Services
    List<String> getHistoricalMetrics(String instanceId, String queueId);
    List<String> getCurrentMetrics(String instanceId);
    ConnectQueueMetricDTO getQueueMetrics(String instanceId, String queueId);
    ConnectAgentMetricDTO getAgentMetrics(String instanceArn, String agentId);
    public Map<String, ConnectQueueInfoDTO> getQueueUserCounts(String instanceId);
}
