package com.springboot.connectmate.services.impl;

import com.amazonaws.services.connect.model.AgentContactReference;
import com.amazonaws.services.connect.model.QueueSummary;
import com.amazonaws.services.connect.model.UserData;
import com.springboot.connectmate.services.AmazonConnectInterpreterService;
import com.springboot.connectmate.services.AmazonConnectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AmazonConnectInterpreterServiceImpl implements AmazonConnectInterpreterService {

    private final ModelMapper mapper;
    private final AmazonConnectService amazonConnectService;

    @Autowired
    public AmazonConnectInterpreterServiceImpl(ModelMapper mapper, AmazonConnectService amazonConnectService) {
        this.mapper = mapper;
        this.amazonConnectService = amazonConnectService;
    }

    public Map<String, Map<String, Object>> getQueueUserCounts(String instanceId) {
        List<UserData> userDataList = amazonConnectService.getCurrentUserData(instanceId);
        Map<String, Map<String, Object>> queueInfo = new HashMap<>();

        if (userDataList != null) {
            for (UserData userData : userDataList) {
                String userId = userData.getUser().getId();
                for (AgentContactReference contact : userData.getContacts()) {
                    String queueId = contact.getQueue().getId();
                    queueInfo.putIfAbsent(queueId, new HashMap<>());
                    queueInfo.get(queueId).putIfAbsent("users", new HashSet<>());
                    queueInfo.get(queueId).putIfAbsent("contactCount", 0);

                    Set<String> users = (Set<String>) queueInfo.get(queueId).get("users");
                    users.add(userId);

                    int count = (int) queueInfo.get(queueId).get("contactCount");
                    queueInfo.get(queueId).put("contactCount", count + 1);
                }
            }
        }

        List<QueueSummary> queueSummaries = amazonConnectService.listQueues(instanceId);

        if (queueSummaries != null) {
            for (QueueSummary queueSummary : queueSummaries) {
                String queueId = queueSummary.getId();
                if (!queueInfo.containsKey(queueId)) {
                    Map<String, Object> newQueueInfo = new HashMap<>();
                    newQueueInfo.put("users", new HashSet<>());
                    newQueueInfo.put("contactCount", 0);
                    queueInfo.put(queueId, newQueueInfo);
                }
            }
        }

        return queueInfo;
    }
}
