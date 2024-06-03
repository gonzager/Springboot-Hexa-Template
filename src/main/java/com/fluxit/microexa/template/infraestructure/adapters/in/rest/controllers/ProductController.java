package com.fluxit.microexa.template.infraestructure.adapters.in.rest.controllers;

import com.fluxit.microexa.template.application.services.IProductService;
import com.fluxit.microexa.template.domain.models.Product;
import com.fluxit.microexa.template.infraestructure.adapters.in.rest.dtos.TemplateResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final IProductService productService;


    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "Get All Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of Template",
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TemplateResponseDTO.class)
                    )}
            )
    })
    public List<Product> getTemplates() {
        return productService.getProducts();
    }
}
