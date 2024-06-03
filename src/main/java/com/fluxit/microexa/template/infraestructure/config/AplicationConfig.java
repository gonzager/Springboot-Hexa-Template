package com.fluxit.microexa.template.infraestructure.config;

import com.fluxit.microexa.template.application.services.ILoggingService;
import com.fluxit.microexa.template.application.services.IProductService;
import com.fluxit.microexa.template.application.services.ITemplateService;
import com.fluxit.microexa.template.application.services.impl.ProductService;
import com.fluxit.microexa.template.application.services.impl.TemplateService;
import com.fluxit.microexa.template.application.usecases.CreateProductUseCase;
import com.fluxit.microexa.template.application.usecases.CreateTemplateUseCase;
import com.fluxit.microexa.template.application.usecases.DeleteTemplateUseCase;
import com.fluxit.microexa.template.application.usecases.GetProductUseCase;
import com.fluxit.microexa.template.application.usecases.RetrieveTemplateUseCase;

import com.fluxit.microexa.template.domain.ports.out.IProductRepositoryPort;
import com.fluxit.microexa.template.domain.ports.out.ITemplateRepositoryPort;
import com.fluxit.microexa.template.infraestructure.adapters.out.persistence.repositories.ProductRepositoryAdapter;
import com.fluxit.microexa.template.infraestructure.adapters.out.persistence.repositories.TemplateResositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class AplicationConfig {

    @Bean
    public ITemplateRepositoryPort templateRepositoryPort(
            TemplateResositoryAdapter templateResositoryAdapter
    ) {
        return templateResositoryAdapter;
    }

    @Bean
    public IProductRepositoryPort productRepositoryPort(
            ProductRepositoryAdapter productRepositoryAdapter
    ) {
        return productRepositoryAdapter;
    }


    @Bean
    @DependsOn("loggingService")
    public ITemplateService templateService(ITemplateRepositoryPort templateRepositoryPort, ILoggingService loggingService) {
        return new TemplateService(
                new RetrieveTemplateUseCase(templateRepositoryPort),
                new CreateTemplateUseCase(templateRepositoryPort),
                new DeleteTemplateUseCase(templateRepositoryPort),
                loggingService
        );
    }

    @Bean
    public IProductService productService(IProductRepositoryPort productRepositoryPort) {
        return new ProductService(
                new CreateProductUseCase(productRepositoryPort),
                new GetProductUseCase(productRepositoryPort)
        );
    }

}
