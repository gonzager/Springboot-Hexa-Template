package com.fluxit.microexa.template.application.usecases;

import com.fluxit.microexa.template.domain.models.Template;
import com.fluxit.microexa.template.domain.ports.in.ICreateTemplate;
import com.fluxit.microexa.template.domain.ports.out.ITemplateRepositoryPort;

public class CreateTemplateUseCase implements ICreateTemplate {

    private final ITemplateRepositoryPort templateRepository;

    public CreateTemplateUseCase(ITemplateRepositoryPort templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Override
    public Template createTemplate(Template template) {
        return templateRepository.createTemplate(template);
    }
}
