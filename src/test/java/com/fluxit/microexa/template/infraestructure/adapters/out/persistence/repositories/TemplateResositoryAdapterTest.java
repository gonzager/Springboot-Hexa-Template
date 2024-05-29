package com.fluxit.microexa.template.infraestructure.adapters.out.persistence.repositories;

import com.fluxit.microexa.template.domain.models.Template;
import com.fluxit.microexa.template.domain.ports.in.ICreateTemplate;
import com.fluxit.microexa.template.infraestructure.adapters.in.rest.exceptions.NotFoundException;
import com.fluxit.microexa.template.infraestructure.adapters.out.persistence.entities.TemplateEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@DisplayName("Template ResositoryAdapter Test")
class TemplateResositoryAdapterTest {

    @Autowired ITemplateRepository templateRepository;

    private Template createTemplateWithoutId(Long externalReferenceId){
        return Template.builder()
                .name("Test Template")
                .externalReferenceId(externalReferenceId)
                .build();
    }
    @Test
    void createTemplate() {
        var template = createTemplateWithoutId(0L);
        assertNotNull(
                templateRepository.save(TemplateEntity.of(template)).toTemplate().getId()
        );
    }

    @Test
    void getAllTemplates() {
        var template = createTemplateWithoutId(1L);
        templateRepository.save(TemplateEntity.of(template)).toTemplate();
        assertEquals(1, templateRepository.findAll().size());
    }

    @Test
    void getTemplateById() {
        var template = createTemplateWithoutId(0L);
        template = templateRepository.save(TemplateEntity.of(template)).toTemplate();
        assertEquals(
                template.getId(),
                templateRepository.findById(template.getId()).orElseThrow().getId()
        );
    }

    @Test
    void getTemplateByIsEmpty() {
        assertTrue( templateRepository.findById(UUID.randomUUID()).isEmpty());
    }

    @Test
    void deleteTemplateById() {
        var template1 = createTemplateWithoutId(1L);
        templateRepository.save(TemplateEntity.of(template1)).toTemplate();

        var template2 = createTemplateWithoutId(0L);
        template2 = templateRepository.save(TemplateEntity.of(template2)).toTemplate();
        templateRepository.deleteById(template2.getId());

        assertEquals(1, templateRepository.findAll().size());
    }
}