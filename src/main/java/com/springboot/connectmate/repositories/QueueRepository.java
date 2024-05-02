package com.springboot.connectmate.repositories;
import com.springboot.connectmate.models.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QueueRepository extends JpaRepository<Queue, Long> {

    @Procedure(name = "sp_get_queue_agents")
    List<Object[]> getQueueAgents(@Param("queueId") Long queueId);

    @Query(value = " SELECT * FROM v_agents_from_queue", nativeQuery = true)
    List<Object[]> getCompleteQueues();
}
