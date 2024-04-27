package com.springboot.connectmate.repositories;

import com.springboot.connectmate.models.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueRepository extends JpaRepository<Queue, Long> {
}
