package com.fluxit.microexa.template.infraestructure.config;

import com.fluxit.microexa.template.application.services.IExternalService;
import com.fluxit.microexa.template.application.services.ILoggingService;
import com.fluxit.microexa.template.application.services.impl.ExternalService;
import com.fluxit.microexa.template.application.usecases.GetAdditionalTemplateUserInfoUseCase;
import com.fluxit.microexa.template.infraestructure.adapters.out.rest.RestExternalAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestConfig {
    private final Integer connectTimeout;
    private final Integer readTimeout;
    public RestConfig(@Value("${app.api.rest.connecttimeout}") Integer connectTimeout , @Value("${app.api.rest.readTimeout}") Integer readTimeout){
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(connectTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    @Bean
    @DependsOn("loggingService")
    public IExternalService externalService(RestTemplate restTemplate, ILoggingService loggingService) {
        return new ExternalService(
                new GetAdditionalTemplateUserInfoUseCase(
                        new RestExternalAdapter(restTemplate)
                ),
                loggingService
        );
    }

}