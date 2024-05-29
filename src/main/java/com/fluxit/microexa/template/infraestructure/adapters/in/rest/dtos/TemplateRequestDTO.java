package com.fluxit.microexa.template.infraestructure.adapters.in.rest.dtos;

import com.fluxit.microexa.template.domain.models.Template;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateRequestDTO {

    @NotEmpty( message = "name is mandatory")
    private String name;
    @NotNull( message = "externalReferenceId is mandatory")
    @Positive( message = "externalReferenceId cannot be negative")
    private Long externalReferenceId;

    public Template toTemplate() {
        return Template.builder()
                .name(name)
                .externalReferenceId(externalReferenceId)
                .build();
    }
}
