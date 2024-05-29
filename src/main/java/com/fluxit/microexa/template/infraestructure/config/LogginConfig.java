package com.fluxit.microexa.template.infraestructure.config;

import com.fluxit.microexa.template.application.services.ILoggingService;
import com.fluxit.microexa.template.infraestructure.adapters.out.loggin.Slf4jLoggingAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogginConfig {
    @Bean
    public ILoggingService loggingService() {
        return new Slf4jLoggingAdapter();
    }
}
