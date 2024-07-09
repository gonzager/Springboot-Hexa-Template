package com.fluxit.microexa.template.infraestructure.adapters.in.rest.dtos;

import lombok.Data;

@Data
public class TemplateWithProductDTO {
    private final TemplateResponseDTO template;
    private final ProductoSinFabricanteDTO producto;

    public TemplateWithProductDTO(TemplateResponseDTO templateResponseDTO, ProductoSinFabricanteDTO productoSinFabricanteDTO) {
        this.template = templateResponseDTO;
        this.producto = productoSinFabricanteDTO;
    }
}
