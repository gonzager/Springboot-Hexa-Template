package com.fluxit.microexa.template.application.services.impl;

import com.fluxit.microexa.template.application.services.ILoggingService;
import com.fluxit.microexa.template.application.services.ITemplateService;
import com.fluxit.microexa.template.domain.models.Template;
import com.fluxit.microexa.template.domain.ports.in.ICreateTemplate;
import com.fluxit.microexa.template.domain.ports.in.IDeleteTemplate;
import com.fluxit.microexa.template.domain.ports.in.IRetrieveTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TemplateService implements ITemplateService {

    private final IRetrieveTemplate retrieveTemplate;
    private final ICreateTemplate createTemplate;
    private final IDeleteTemplate deleteTemplate;
    private final ILoggingService loggingService;

    public TemplateService(IRetrieveTemplate retrieveTemplate,
                           ICreateTemplate createTemplate,
                           IDeleteTemplate deleteTemplate, ILoggingService loggingService) {
        this.retrieveTemplate = retrieveTemplate;
        this.createTemplate = createTemplate;
        this.deleteTemplate = deleteTemplate;
        this.loggingService = loggingService;
    }

    @Override
    public Template createTemplate(Template template) {
        loggingService.logDebug("Template createTemplate({})", template);
        return createTemplate.createTemplate(template);
    }

    @Override
    public List<Template> getAllTemplates() {
        loggingService.logDebug("Template getAllTemplates()");
        return retrieveTemplate.getAllTemplates();
    }

    @Override
    public Optional<Template> getTemplateById(UUID id) {
        loggingService.logDebug("Template getTemplateById({})", id);
        return retrieveTemplate.getTemplateById(id);
    }

    @Override
    public void deleteTemplateById(UUID id) {
        loggingService.logDebug( "deleteTemplateById({})", id);
        deleteTemplate.deleteTemplateById(id);
    }

}
