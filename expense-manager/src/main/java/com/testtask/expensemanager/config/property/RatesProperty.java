package com.testtask.expensemanager.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(value = "feign.rates")
public class RatesProperty {

    private Twelvedata twelvedata;

    @Getter
    @Setter
    public static class Twelvedata {
        private String url;
        private String key;
    }
}
