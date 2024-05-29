package com.fluxit.microexa.template.application.usecases;

import com.fluxit.microexa.template.domain.ports.in.IDeleteTemplate;
import com.fluxit.microexa.template.domain.ports.out.ITemplateRepositoryPort;

import java.util.UUID;

public class DeleteTemplateUseCase implements IDeleteTemplate {

    private final ITemplateRepositoryPort templateRepository;

    public DeleteTemplateUseCase(ITemplateRepositoryPort templateRepository) {
        this.templateRepository = templateRepository;
    }


    @Override
    public void deleteTemplateById(UUID id) {
        templateRepository.deleteTemplateById(id);
    }
}
