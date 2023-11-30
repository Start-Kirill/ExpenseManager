package com.testtask.expensemanager.services.api;


import com.testtask.expensemanager.config.FeignConfig;
import com.testtask.expensemanager.core.dtos.ExternalRateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Map;

@FeignClient(value = "rates", url = "${feign.rates.twelvedata.url}", configuration = FeignConfig.class)
public interface IRateClient {

    @GetMapping
    Map<String, ExternalRateDto> get(@RequestParam String symbol,
                                     @RequestParam String interval,
                                     @RequestParam(name = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                     @RequestParam(name = "end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                     @RequestParam String apikey);


}
