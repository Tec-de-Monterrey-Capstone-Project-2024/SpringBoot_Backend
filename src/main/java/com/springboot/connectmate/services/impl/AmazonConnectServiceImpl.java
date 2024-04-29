package com.springboot.connectmate.services.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.connect.AmazonConnect;
import com.amazonaws.services.connect.AmazonConnectClientBuilder;
import com.amazonaws.services.connect.model.*;
import com.springboot.connectmate.services.AmazonConnectService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmazonConnectServiceImpl implements AmazonConnectService {

    @Value("${aws.accessKeyId}")
    private String accessKeyId;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    private AmazonConnect amazonConnectClient() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, secretKey);
        return AmazonConnectClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    @Override
    public List<String> listConnectInstances() {
        ListInstancesRequest listInstancesRequest = new ListInstancesRequest();
        ListInstancesResult listInstancesResult = amazonConnectClient().listInstances(listInstancesRequest);
        return listInstancesResult.getInstanceSummaryList().stream()
                .map(instanceSummary -> "Instance ARN: " + instanceSummary.getArn() + ", Id: " + instanceSummary.getId())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listQueues(String instanceId) {
        ListQueuesRequest listQueuesRequest = new ListQueuesRequest().withInstanceId(instanceId);
        ListQueuesResult listQueuesResult = amazonConnectClient().listQueues(listQueuesRequest);
        return listQueuesResult.getQueueSummaryList().stream()
                .map(queueSummary -> "Queue ARN: " + queueSummary.getArn() + ", Name: " + queueSummary.getName())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listAgents(String instanceId) {
        ListAgentStatusesRequest listAgentStatusesRequest = new ListAgentStatusesRequest().withInstanceId(instanceId);
        ListAgentStatusesResult listAgentStatusesResult = amazonConnectClient().listAgentStatuses(listAgentStatusesRequest);
        return listAgentStatusesResult.getAgentStatusSummaryList().stream()
                .map(agentSummary -> "Agent ARN: " + agentSummary.getArn() + ", Username: " + agentSummary.getName())
                .collect(Collectors.toList());
    }



}
