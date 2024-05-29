package com.fluxit.microexa.template.infraestructure.adapters.out.persistence.repositories;

import com.fluxit.microexa.template.domain.models.Template;
import com.fluxit.microexa.template.domain.ports.out.ITemplateRepositoryPort;
import com.fluxit.microexa.template.infraestructure.adapters.out.persistence.entities.TemplateEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TemplateResositoryAdapter implements ITemplateRepositoryPort {

    private final ITemplateRepository templateRepository;

    public TemplateResositoryAdapter(ITemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Override
    public Template createTemplate(Template template) {
        return templateRepository.save(TemplateEntity.of(template)).toTemplate();
    }

    @Override
    public List<Template> getAllTemplates() {
        return templateRepository.findAll().stream().map(TemplateEntity::toTemplate).toList();
    }

    @Override
    public Optional<Template> getTemplateById(UUID id) {
        return templateRepository.findById(id).map(TemplateEntity::toTemplate);
    }


    @Override
    public void deleteTemplateById(UUID id) {
        templateRepository.deleteById(id);
    }
}
