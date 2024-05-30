package com.springboot.connectmate.services.impl;

import com.amazonaws.services.connect.AmazonConnect;
import com.amazonaws.services.connect.model.*;
import com.amazonaws.services.connect.model.Queue;
import com.springboot.connectmate.dtos.AmazonConnect.*;
import com.springboot.connectmate.dtos.AmazonConnect.ConnectSecurityProfileDTO;
import com.springboot.connectmate.services.AmazonConnectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AmazonConnectServiceImpl implements AmazonConnectService {

    private final AmazonConnect amazonConnectClient;
    private final ModelMapper mapper;

    @Autowired
    public AmazonConnectServiceImpl(AmazonConnect amazonConnectClient, ModelMapper mapper) {
        this.amazonConnectClient = amazonConnectClient;
        this.mapper = mapper;
    }

    @Override
    public List<InstanceSummary> listConnectInstances() {
        ListInstancesRequest listInstancesRequest = new ListInstancesRequest();
        ListInstancesResult listInstancesResult = amazonConnectClient.listInstances(listInstancesRequest);
        return listInstancesResult.getInstanceSummaryList().stream()
                .map(instance ->  mapper.map(instance, InstanceSummary.class))
                .collect(Collectors.toList());
    }

    @Override
    public Instance getConnectInstance(String instanceId) {
        DescribeInstanceRequest describeInstanceRequest = new DescribeInstanceRequest().withInstanceId(instanceId);
        DescribeInstanceResult describeInstanceResult = amazonConnectClient.describeInstance(describeInstanceRequest);
        return describeInstanceResult.getInstance();
    }

    @Override
    public List<ConnectQueueDTO> listQueues(String instanceId) {
        ListQueuesRequest listQueuesRequest = new ListQueuesRequest().withInstanceId(instanceId);
        ListQueuesResult listQueuesResult = amazonConnectClient.listQueues(listQueuesRequest);
        return listQueuesResult.getQueueSummaryList().stream()
                .map(queue -> mapper.map(queue, ConnectQueueDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AgentStatusSummary> listAgentsStatuses(String instanceId) {
        ListAgentStatusesRequest listAgentStatusesRequest = new ListAgentStatusesRequest().withInstanceId(instanceId);
        ListAgentStatusesResult listAgentStatusesResult = amazonConnectClient.listAgentStatuses(listAgentStatusesRequest);
        return listAgentStatusesResult.getAgentStatusSummaryList().stream()
                .map(agent -> mapper.map(agent, AgentStatusSummary.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserSummary> listUsers(String instanceId) {
        ListUsersRequest listUsersRequest = new ListUsersRequest().withInstanceId(instanceId);
        ListUsersResult listUsersResult = amazonConnectClient.listUsers(listUsersRequest);
        return listUsersResult.getUserSummaryList().stream()
                .map(user -> mapper.map(user, UserSummary.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoutingProfileSummary> listRoutingProfiles(String instanceId) {
        ListRoutingProfilesRequest listRoutingProfilesRequest = new ListRoutingProfilesRequest().withInstanceId(instanceId);
        ListRoutingProfilesResult listRoutingProfilesResult = amazonConnectClient.listRoutingProfiles(listRoutingProfilesRequest);
        return listRoutingProfilesResult.getRoutingProfileSummaryList().stream()
                .map(routingProfile -> mapper.map(routingProfile, RoutingProfileSummary.class))
                .collect(Collectors.toList());
    }

    @Override
    public User getUserDescription(String instanceId, String userId) {
        DescribeUserRequest describeUserRequest = new DescribeUserRequest()
                .withInstanceId(instanceId)
                .withUserId(userId);

        DescribeUserResult describeUserResult = amazonConnectClient.describeUser(describeUserRequest);
        return describeUserResult.getUser();
    }

    @Override
    public List<ConnectCurrentUserDataDTO> getCurrentUserData(String instanceId) {
        UserDataFilters userDataFilters = new UserDataFilters()
                .withQueues(listQueues(instanceId).stream()
                        .map(ConnectQueueDTO::getId)
                        .collect(Collectors.toList()));

        GetCurrentUserDataRequest getCurrentUserDataRequest = new GetCurrentUserDataRequest()
                .withFilters(userDataFilters)
                .withInstanceId(instanceId);

        GetCurrentUserDataResult getCurrentUserDataResult = amazonConnectClient.getCurrentUserData(getCurrentUserDataRequest);

        return getCurrentUserDataResult.getUserDataList().stream()
                .map(userData -> {
                    ConnectCurrentUserDataDTO currentDto = new ConnectCurrentUserDataDTO();
                    currentDto.setUserId(userData.getUser().getId());
                    currentDto.setStatusName(userData.getStatus().getStatusName());

                    List<ConnectContactDTO> contactDTOs = userData.getContacts().stream()
                            .map(contact -> {
                                ConnectContactDTO contactDTO = new ConnectContactDTO();
                                contactDTO.setContactId(contact.getContactId());
                                contactDTO.setQueueId(contact.getQueue().getId());
                                return contactDTO;
                            })
                            .collect(Collectors.toList());

                    currentDto.setContacts(contactDTOs);
                    return currentDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Queue describeQueue(String instanceId, String queueId) {
        DescribeQueueRequest describeQueueRequest = new DescribeQueueRequest()
                .withInstanceId(instanceId)
                .withQueueId(queueId);

        DescribeQueueResult describeQueueResult = amazonConnectClient.describeQueue(describeQueueRequest);

        return describeQueueResult.getQueue();
    }

    // Metric Service Implementations
    // Not Yet Functional
    @Override
    public List<String> getHistoricalMetrics(String instanceId, String queueId) {

        Threshold threshold = new Threshold()
                .withComparison(Comparison.LT)
                .withThresholdValue(20.00);

        List<HistoricalMetric> metrics = new ArrayList<>();
        metrics.add(new HistoricalMetric()
                .withName(HistoricalMetricName.SERVICE_LEVEL)
                .withStatistic(Statistic.AVG)
                .withUnit(Unit.PERCENT)
                .withThreshold(threshold)
        );
        metrics.add(new HistoricalMetric()
                .withName(HistoricalMetricName.HANDLE_TIME)
                .withStatistic(Statistic.AVG)
                .withUnit(Unit.SECONDS)
        );
        metrics.add(new HistoricalMetric()
                .withName(HistoricalMetricName.CONTACTS_HANDLED)
                .withStatistic(Statistic.SUM)
                .withUnit(Unit.COUNT)
        );
        Filters filters = new Filters()
                .withChannels(Channel.VOICE)
                .withQueues(queueId);

        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime endTime = now.withSecond(0).withNano(0).minusMinutes(now.getMinute() % 5);
        ZonedDateTime startTime = endTime.minusHours(23);

        GetMetricDataRequest getMetricDataRequest = new GetMetricDataRequest()
                .withInstanceId(instanceId)
                .withHistoricalMetrics(metrics)
                .withFilters(filters)
                .withStartTime(Date.from(startTime.toInstant()))
                .withEndTime(Date.from(endTime.toInstant()));

        GetMetricDataResult getMetricDataResult = amazonConnectClient.getMetricData(getMetricDataRequest);
        System.out.println(getMetricDataResult.toString());
        return getMetricDataResult.getMetricResults().stream()
                .map(metricResult -> metricResult.toString())
                .collect(Collectors.toList());
    }

    @Override
    public ConnectQueueMetricDTO getQueueMetrics(String instanceArn, String queueId) {

        ThresholdV2 threshold = new ThresholdV2()
                .withComparison("LT")
                .withThresholdValue(20.00);

        List<MetricV2> metrics = new ArrayList<>();
        metrics.add(new MetricV2()
                .withName("SERVICE_LEVEL")
                .withThreshold(threshold)
        );

        metrics.add(new MetricV2()
                .withName("ABANDONMENT_RATE")
        );

        metrics.add(new MetricV2()
                .withName("AGENT_SCHEDULE_ADHERENCE")
        );

        metrics.add(new MetricV2()
                .withName("AVG_HANDLE_TIME")
        );

        metrics.add(new MetricV2()
                .withName("AVG_AFTER_CONTACT_WORK_TIME")
        );

        metrics.add(new MetricV2()
                .withName("AVG_RESOLUTION_TIME")
        );

        metrics.add(new MetricV2()
                .withName("AVG_QUEUE_ANSWER_TIME")
        );

        List<FilterV2> filters = new ArrayList<>();

        filters.add( new FilterV2()
                .withFilterKey("QUEUE")
                .withFilterValues(Collections.singletonList(queueId))
        );

        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime endTime = now.withSecond(0).withNano(0).minusMinutes(now.getMinute() % 5);
        ZonedDateTime startTime = endTime.minusHours(23);

        GetMetricDataV2Request getMetricDataV2Request = new GetMetricDataV2Request()
                .withResourceArn(instanceArn)
                .withMetrics(metrics)
                .withFilters(filters)
                .withStartTime(Date.from(startTime.toInstant()))
                .withEndTime(Date.from(endTime.toInstant()));

        GetMetricDataV2Result getMetricDataResult = amazonConnectClient.getMetricDataV2(getMetricDataV2Request);
        System.out.println(getMetricDataResult.toString());

        ConnectQueueMetricDTO metricsDTO = new ConnectQueueMetricDTO();
        metricsDTO.setAbandonmentRate(getMetricDataResult.getMetricResults().get(0).getCollections().get(0).getValue());
        metricsDTO.setAvgHandleTime(getMetricDataResult.getMetricResults().get(0).getCollections().get(1).getValue());
        metricsDTO.setAvgAfterContactWorkTime(getMetricDataResult.getMetricResults().get(0).getCollections().get(2).getValue());
        metricsDTO.setAvgResolutionTime(getMetricDataResult.getMetricResults().get(0).getCollections().get(3).getValue());
        metricsDTO.setAvgQueueAnswerTime(getMetricDataResult.getMetricResults().get(0).getCollections().get(4).getValue());
        metricsDTO.setServiceLevel(getMetricDataResult.getMetricResults().get(0).getCollections().get(5).getValue());
        return metricsDTO;
    }

    @Override
    public ConnectAgentMetricDTO getAgentMetrics(String instanceArn, String agentId) {

        List<MetricV2> metrics = new ArrayList<>();

        metrics.add(new MetricV2()
                .withName("ABANDONMENT_RATE")
        );

        metrics.add(new MetricV2()
                .withName("AGENT_OCCUPANCY")
        );

        metrics.add(new MetricV2()
                .withName("AVG_HANDLE_TIME")
        );

        metrics.add(new MetricV2()
                .withName("AVG_AFTER_CONTACT_WORK_TIME")
        );

        List<FilterV2> filters = new ArrayList<>();

        filters.add(new FilterV2()
                .withFilterKey("AGENT")
                .withFilterValues(Collections.singletonList(agentId))
        );

        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime endTime = now.withSecond(0).withNano(0).minusMinutes(now.getMinute() % 5);
        ZonedDateTime startTime = endTime.minusHours(23);

        GetMetricDataV2Request getMetricDataV2Request = new GetMetricDataV2Request()
                .withResourceArn(instanceArn)
                .withMetrics(metrics)
                .withFilters(filters)
                .withEndTime(Date.from(endTime.toInstant()))
                .withStartTime(Date.from(startTime.toInstant()));

        GetMetricDataV2Result getMetricDataResult = amazonConnectClient.getMetricDataV2(getMetricDataV2Request);
        System.out.println(getMetricDataResult.toString());

        ConnectAgentMetricDTO metricsDTO = new ConnectAgentMetricDTO();
        metricsDTO.setAbandonmentRate(getMetricDataResult.getMetricResults().get(0).getCollections().get(0).getValue());
        metricsDTO.setAvgHandleTime(getMetricDataResult.getMetricResults().get(0).getCollections().get(1).getValue());
        metricsDTO.setAvgAfterContactWorkTime(getMetricDataResult.getMetricResults().get(0).getCollections().get(2).getValue());
        metricsDTO.setAgentOccupancy(getMetricDataResult.getMetricResults().get(0).getCollections().get(3).getValue());
        return metricsDTO;
    }

    @Override
    public List<String> getCurrentMetrics(String instanceId) {

        List<CurrentMetric> currentMetrics = new ArrayList<>();
        currentMetrics.add(new CurrentMetric()
                .withName(CurrentMetricName.AGENTS_AVAILABLE)
        );

        GetCurrentMetricDataRequest getCurrentMetricDataRequest = new GetCurrentMetricDataRequest()
                .withInstanceId(instanceId)
                .withCurrentMetrics();

        GetCurrentMetricDataResult getCurrentMetricDataResult = amazonConnectClient.getCurrentMetricData(getCurrentMetricDataRequest);
        return getCurrentMetricDataResult.getMetricResults().stream()
                .map(metric-> metric.toString())
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, ConnectQueueInfoDTO> getQueueUserCounts(String instanceId) {
        List<ConnectCurrentUserDataDTO> userDataList = getCurrentUserData(instanceId);
        Map<String, ConnectQueueInfoDTO> queueInfoMap = new HashMap<>();

        List<ConnectQueueDTO> allQueues = listQueues(instanceId);
        if (allQueues != null) {
            for (ConnectQueueDTO queue : allQueues) {
                queueInfoMap.put(queue.getId(), new ConnectQueueInfoDTO());
            }
        }

        if (userDataList != null) {
            for (ConnectCurrentUserDataDTO userData : userDataList) {
                String userId = userData.getUserId();
                for (ConnectContactDTO contact : userData.getContacts()) {
                    String queueId = contact.getQueueId();
                    queueInfoMap.putIfAbsent(queueId, new ConnectQueueInfoDTO());

                    queueInfoMap.get(queueId).getUsers().add(userId);

                    queueInfoMap.get(queueId).setContactCount(queueInfoMap.get(queueId).getContactCount() + 1);
                }
            }
        }

        return queueInfoMap;
    }

    public List<ConnectSecurityProfileDTO> getUserSecurityProfileIds (String instanceId, String userId){
        DescribeUserRequest request = new DescribeUserRequest()
                .withInstanceId(instanceId)
                .withUserId(userId);

        DescribeUserResult response = amazonConnectClient.describeUser(request);
        User user = response.getUser();

        List<String> securityProfileIds = user.getSecurityProfileIds();
        List<ConnectSecurityProfileDTO> userRoles = new ArrayList<>();

        for (String roleId : securityProfileIds) {
            DescribeSecurityProfileRequest securityProfileRequest = new DescribeSecurityProfileRequest()
                    .withInstanceId(instanceId)
                    .withSecurityProfileId(roleId);

            DescribeSecurityProfileResult securityProfileResult = amazonConnectClient.describeSecurityProfile(securityProfileRequest);
            SecurityProfile securityProfile = securityProfileResult.getSecurityProfile();

            userRoles.add(mapper.map(securityProfile, ConnectSecurityProfileDTO.class));
        }

        return userRoles;
    }
}
