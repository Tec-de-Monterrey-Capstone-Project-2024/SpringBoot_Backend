package com.springboot.connectmate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.connectmate.models.Alert;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
}