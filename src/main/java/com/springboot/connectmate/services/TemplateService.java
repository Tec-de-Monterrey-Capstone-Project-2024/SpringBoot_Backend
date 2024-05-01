package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.Template.TemplateDTO;

public interface TemplateService {

    TemplateDTO getRightTemplateByBreachId(Long breachId);
}
