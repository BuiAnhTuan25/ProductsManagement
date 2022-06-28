package com.ghtk.tuanba59.config;

import com.ghtk.tuanba59.model.validator.ProductValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {
    @Bean
    public ProductValidator productValidator() {
        return new ProductValidator();
    }
}
