package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.Template.TemplateDTO;
import com.springboot.connectmate.models.Template;
import com.springboot.connectmate.repositories.QueueRepository;
import com.springboot.connectmate.repositories.TemplateRepository;
import com.springboot.connectmate.services.TemplateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl implements TemplateService {

    private final ModelMapper mapper;
    private final TemplateRepository templateRepository;

    @Autowired
    public TemplateServiceImpl(TemplateRepository templateRepository, ModelMapper mapper) {
        this.templateRepository = templateRepository;
        this.mapper = mapper;
    }

    @Override
    public TemplateDTO getRightTemplateByBreachId(Long breachId) {
        /**
         * Right now its just a query
         * But once we have all templates defined.
         * We must adapt the logic to choose whether to return a positive template or not
         */
        // All metric MUST have a template, no need to check if template is null
        Template template = templateRepository.findTemplateByBreachIdSQL(breachId);
        return mapper.map(template, TemplateDTO.class);
    }
}
