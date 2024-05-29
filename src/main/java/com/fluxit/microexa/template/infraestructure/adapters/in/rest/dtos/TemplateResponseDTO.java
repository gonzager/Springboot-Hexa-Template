package com.fluxit.microexa.template.infraestructure.adapters.in.rest.dtos;

import com.fluxit.microexa.template.domain.models.Template;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateResponseDTO {
    private UUID id;
    private String name;
    private Boolean available;
    private Long externalReferenceId;

    public static TemplateResponseDTO from(Template template) {
        return TemplateResponseDTO.builder()
                .id(template.getId())
                .name(template.getName())
                .available(template.isAvailable())
                .externalReferenceId(template.getExternalReferenceId())
                .build();
    }
}
