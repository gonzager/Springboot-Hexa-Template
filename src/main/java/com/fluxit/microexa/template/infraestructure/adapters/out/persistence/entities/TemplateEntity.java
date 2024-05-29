package com.fluxit.microexa.template.infraestructure.adapters.out.persistence.entities;

import com.fluxit.microexa.template.domain.models.Template;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "TEMPLATE")
public class TemplateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID")
    UUID id;

    @Column
    String name;

    @Column(nullable = false)
    Long externalReferenceId;

    public static TemplateEntity of(Template template) {
        return TemplateEntity.builder()
                .id(template.getId())
                .name(template.getName())
                .externalReferenceId(template.getExternalReferenceId())
                .build();
    }

    public Template toTemplate() {
        return Template.builder()
                .id(id)
                .name(name)
                .externalReferenceId(externalReferenceId)
                .build();
    }
}
