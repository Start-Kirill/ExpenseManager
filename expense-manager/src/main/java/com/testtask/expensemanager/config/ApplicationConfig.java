package com.testtask.expensemanager.config;

import com.testtask.expensemanager.endpoints.web.support.spring.converters.LimitToLimitDtoConverter;
import com.testtask.expensemanager.endpoints.web.support.spring.converters.TransactionToTransactionDtoConverter;
import com.testtask.expensemanager.services.support.spring.converters.CurrencyCreateDtoToCurrencyConverter;
import com.testtask.expensemanager.services.support.spring.converters.LimitCreateDtoToLimitConverter;
import com.testtask.expensemanager.services.support.spring.converters.RateCreateDtoToRateConverter;
import com.testtask.expensemanager.services.support.spring.converters.TransactionCreateDtoToTransactionConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new TransactionToTransactionDtoConverter());
        registry.addConverter(new LimitToLimitDtoConverter());
        registry.addConverter(new CurrencyCreateDtoToCurrencyConverter());
        registry.addConverter(new RateCreateDtoToRateConverter());
        registry.addConverter(new LimitCreateDtoToLimitConverter());
        registry.addConverter(new TransactionCreateDtoToTransactionConverter());
    }


}
