package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.User.UserDTO;

import java.util.List;

public interface QueueService {

    List<UserDTO> getQueueAgents(Long queueId);

}
