package com.fluxit.microexa.template.infraestructure.adapters.out.rest;

import com.fluxit.microexa.template.infraestructure.adapters.in.rest.dtos.ProductoSinFabricanteDTO;
import com.fluxit.microexa.template.infraestructure.adapters.in.rest.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.embedded.TomcatVirtualThreadsWebServerFactoryCustomizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class RestExternalProductoAdapter {

   @Value("${info.rest.productourl}")
    private String productoUrl;

    private final RestTemplate restTemplate;

    public RestExternalProductoAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductoSinFabricanteDTO getPExternalProductoById(Long externalReferenceId) {
        String apiUrl = productoUrl + externalReferenceId;
        try {
            ResponseEntity<ProductoSinFabricanteDTO> response = restTemplate.getForEntity(apiUrl, ProductoSinFabricanteDTO.class);
            return response.getBody();
        } catch (Exception e) {
            throw new NotFoundException("No se encontro el producto con id: " + externalReferenceId);
        }
    }

}

