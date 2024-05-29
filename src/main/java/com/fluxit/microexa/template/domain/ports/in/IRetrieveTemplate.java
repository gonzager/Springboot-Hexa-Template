package com.fluxit.microexa.template.domain.ports.in;

import com.fluxit.microexa.template.domain.models.Template;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IRetrieveTemplate {
    List<Template> getAllTemplates();
    Optional<Template> getTemplateById(UUID id);
}
