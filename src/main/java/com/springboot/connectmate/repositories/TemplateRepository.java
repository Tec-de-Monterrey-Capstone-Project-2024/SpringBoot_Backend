package com.springboot.connectmate.repositories;

import com.springboot.connectmate.models.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, Long> {
}
