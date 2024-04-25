package com.springboot.connectmate.services.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.connect.AmazonConnect;
import com.amazonaws.services.connect.AmazonConnectClientBuilder;
import com.amazonaws.services.connect.model.ListInstancesRequest;
import com.amazonaws.services.connect.model.ListInstancesResult;
import com.springboot.connectmate.services.AmazonConnectService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmazonConnectServiceImpl implements AmazonConnectService {
    private String accessKeyId;

    private String secretKey;

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

}
