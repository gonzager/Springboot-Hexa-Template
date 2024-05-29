package com.fluxit.microexa.template.application.usecases;

import com.fluxit.microexa.template.domain.models.Template;
import com.fluxit.microexa.template.domain.ports.in.IRetrieveTemplate;
import com.fluxit.microexa.template.domain.ports.out.ITemplateRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RetrieveTemplateUseCase implements IRetrieveTemplate {

    private final ITemplateRepositoryPort templateRepository;


    public RetrieveTemplateUseCase(ITemplateRepositoryPort templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Override
    public List<Template> getAllTemplates() {
        return templateRepository.getAllTemplates();
    }

    @Override
    public Optional<Template> getTemplateById(UUID id) {
        return templateRepository.getTemplateById(id);
    }

    public List<Template> getAvailablesTemplatesByLogicDomain() {
        return templateRepository.getAllTemplates().stream().filter(Template::isAvailable).toList();
    }
}
