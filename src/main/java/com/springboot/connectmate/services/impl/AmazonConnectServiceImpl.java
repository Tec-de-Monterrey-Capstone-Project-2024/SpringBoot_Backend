package com.springboot.connectmate.services.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.connect.AmazonConnect;
import com.amazonaws.services.connect.AmazonConnectClientBuilder;
import com.amazonaws.services.connect.model.*;
import com.springboot.connectmate.dtos.AmazonConnect.*;
import com.springboot.connectmate.dtos.AmazonConnect.ConnectUserDataDTO;
import com.springboot.connectmate.services.AmazonConnectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AmazonConnectServiceImpl implements AmazonConnectService {

    private final ModelMapper mapper;

    @Value("${aws.accessKeyId}")
    private String accessKeyId;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Autowired
    public AmazonConnectServiceImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    private AmazonConnect amazonConnectClient() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, secretKey);
        return AmazonConnectClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    @Override
    public List<ConnectInstanceDTO> listConnectInstances() {
        ListInstancesRequest listInstancesRequest = new ListInstancesRequest();
        ListInstancesResult listInstancesResult = amazonConnectClient().listInstances(listInstancesRequest);
        return listInstancesResult.getInstanceSummaryList().stream()
                .map(instance ->  mapper.map(instance, ConnectInstanceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ConnectQueueDTO> listQueues(String instanceId) {
        ListQueuesRequest listQueuesRequest = new ListQueuesRequest().withInstanceId(instanceId);
        ListQueuesResult listQueuesResult = amazonConnectClient().listQueues(listQueuesRequest);
        return listQueuesResult.getQueueSummaryList().stream()
                .map(queue -> mapper.map(queue, ConnectQueueDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ConnectAgentDTO> listAgents(String instanceId) {
        ListAgentStatusesRequest listAgentStatusesRequest = new ListAgentStatusesRequest().withInstanceId(instanceId);
        ListAgentStatusesResult listAgentStatusesResult = amazonConnectClient().listAgentStatuses(listAgentStatusesRequest);
        return listAgentStatusesResult.getAgentStatusSummaryList().stream()
                .map(agent -> mapper.map(agent, ConnectAgentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ConnectUserDTO> listUsers(String instanceId) {
        ListUsersRequest listUsersRequest = new ListUsersRequest().withInstanceId(instanceId);
        ListUsersResult listUsersResult = amazonConnectClient().listUsers(listUsersRequest);
        return listUsersResult.getUserSummaryList().stream()
                .map(user -> mapper.map(user, ConnectUserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ConnectRoutingProfileDTO> listRoutingProfiles(String instanceId) {
        ListRoutingProfilesRequest listRoutingProfilesRequest = new ListRoutingProfilesRequest().withInstanceId(instanceId);
        ListRoutingProfilesResult listRoutingProfilesResult = amazonConnectClient().listRoutingProfiles(listRoutingProfilesRequest);
        return listRoutingProfilesResult.getRoutingProfileSummaryList().stream()
                .map(routingProfile -> mapper.map(routingProfile, ConnectRoutingProfileDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getHistoricalMetrics(String instanceId) {

        Threshold threshold = new Threshold()
                .withComparison(Comparison.LT)
                .withThresholdValue(30.00);

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
                .withQueues("f0813607-af92-4a36-91e6-630ababb643c");


        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime endTime = now.withSecond(0).withNano(0).minusMinutes(now.getMinute() % 5);
        ZonedDateTime startTime = endTime.minusHours(23);

        GetMetricDataRequest getMetricDataRequest = new GetMetricDataRequest()
                .withInstanceId(instanceId)
                .withHistoricalMetrics(metrics)
                .withFilters(filters)
                .withStartTime(Date.from(startTime.toInstant()))
                .withEndTime(Date.from(endTime.toInstant()));

        GetMetricDataResult getMetricDataResult = amazonConnectClient().getMetricData(getMetricDataRequest);
        System.out.println(getMetricDataResult.toString());
        return getMetricDataResult.getMetricResults().stream()
                .map(metricResult -> metricResult.toString())
                .collect(Collectors.toList());
    }

    @Override
    public String getTests(String instanceId) {
        GetCurrentMetricDataRequest getCurrentMetricDataRequest = new GetCurrentMetricDataRequest()
                .withInstanceId(instanceId);

        GetMetricDataV2Request getMetricDataV2Request = new GetMetricDataV2Request();


        return "to do: do the controllers/services for these connect get requests";
    }
    @Override
    public String listTests(String instanceId) {
        ListAgentStatusesRequest listAgentStatusesRequest = new ListAgentStatusesRequest().withInstanceId(instanceId);
        ListPhoneNumbersRequest listPhoneNumbersRequest = new ListPhoneNumbersRequest().withInstanceId(instanceId);
        ListUserProficienciesRequest listUserProficienciesRequest = new ListUserProficienciesRequest().withInstanceId(instanceId);
        ListContactEvaluationsRequest listContactEvaluationsRequest = new ListContactEvaluationsRequest().withInstanceId(instanceId);
        return "to do: do the controllers/services for these connect list requests";
    }

    @Override
    public List<ConnectUserDataDTO> getCurrentData(String instanceId) {
        UserDataFilters userDataFilters = new UserDataFilters()
                .withQueues(listQueues(instanceId).stream()
                        .map(ConnectQueueDTO::getId)
                        .collect(Collectors.toList()));

        GetCurrentUserDataRequest getCurrentUserDataRequest = new GetCurrentUserDataRequest()
                .withFilters(userDataFilters)
                .withInstanceId(instanceId);

        GetCurrentUserDataResult getCurrentUserDataResult = amazonConnectClient().getCurrentUserData(getCurrentUserDataRequest);

        return getCurrentUserDataResult.getUserDataList().stream()
                .map(userData -> {
                    ConnectUserDataDTO connectUserDataDTO = new ConnectUserDataDTO();
                    connectUserDataDTO.setUserId(userData.getUser().getId());
                    connectUserDataDTO.setRoutingProfileId(userData.getRoutingProfile().getId());
                    connectUserDataDTO.setQueueId(userData.getContacts().get(0).getQueue().getId());
                    return connectUserDataDTO;
                })
                .collect(Collectors.toList());
    }
}
