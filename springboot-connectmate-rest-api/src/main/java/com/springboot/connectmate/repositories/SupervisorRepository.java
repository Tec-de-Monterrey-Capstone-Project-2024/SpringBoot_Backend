package com.springboot.connectmate.repositories;

import com.springboot.connectmate.models.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {
}
