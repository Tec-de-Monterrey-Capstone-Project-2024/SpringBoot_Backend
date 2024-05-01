package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Queue.QueueDTO;
import java.util.List;

import com.springboot.connectmate.dtos.User.UserDTO;


public interface QueueService {
    List<UserDTO> getQueueAgents(Long queueId);
    List<QueueDTO> getQueues();
}
