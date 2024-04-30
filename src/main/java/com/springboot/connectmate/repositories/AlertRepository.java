package com.springboot.connectmate.repositories;

import com.springboot.connectmate.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    // Aquí puedes definir métodos de consulta personalizados si es necesario
    // Por ejemplo, buscar alertas por categoría de violación o por rango de fechas
}
