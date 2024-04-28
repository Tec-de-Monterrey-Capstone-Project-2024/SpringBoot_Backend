package com.springboot.connectmate.repositories;

import com.springboot.connectmate.dtos.User.UserDTO;
import com.springboot.connectmate.models.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QueueRepository extends JpaRepository<Queue, Long> {

    @Procedure(name = "SP_getQueueAgents")
    List<Object[]> getQueueAgents(@Param("queueId") Long queueId);
}
