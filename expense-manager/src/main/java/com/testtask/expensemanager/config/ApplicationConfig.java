package com.testtask.expensemanager.config;

import com.testtask.expensemanager.endpoints.web.controllers.support.spring.converters.LimitToLimitDtoConverter;
import com.testtask.expensemanager.endpoints.web.controllers.support.spring.converters.TransactionToTransactionDtoConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new TransactionToTransactionDtoConverter());
        registry.addConverter(new LimitToLimitDtoConverter());
    }


}
