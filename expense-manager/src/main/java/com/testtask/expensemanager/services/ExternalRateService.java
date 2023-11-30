package com.testtask.expensemanager.services;

import com.testtask.expensemanager.config.property.RatesProperty;
import com.testtask.expensemanager.core.dtos.ExternalRateCreateDto;
import com.testtask.expensemanager.core.dtos.ExternalRateDto;
import com.testtask.expensemanager.core.enums.ErrorType;
import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.services.api.IExternalRateService;
import com.testtask.expensemanager.services.api.IRateClient;
import com.testtask.expensemanager.services.exceptions.FailedRateAccessException;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ExternalRateService implements IExternalRateService {

    private static final String FAILED_ACCESS_MESSAGE = "Error receiving data from external source. Please try later or contact administrator";

    private static final String DAILY_INTERVAL_NAME = "1day";

    private final IRateClient rateClient;

    private final RatesProperty.Twelvedata twelvedataProps;

    public ExternalRateService(IRateClient rateClient,
                               RatesProperty ratesProperty) {
        this.rateClient = rateClient;
        this.twelvedataProps = ratesProperty.getTwelvedata();
    }


    @Override
    public Map<String, ExternalRateDto> get(ExternalRateCreateDto externalRateCreateDto) {

        List<Pair<String, String>> currencyPairs = externalRateCreateDto.getCurrencyPairs();

        String symbolParam = createSymbolParam(currencyPairs);
        LocalDate startDate = externalRateCreateDto.getStartDate();
        LocalDate endDate = externalRateCreateDto.getEndDate();
        String apiKey = this.twelvedataProps.getKey();

        try {
            return this.rateClient.get(symbolParam, DAILY_INTERVAL_NAME, startDate, endDate, apiKey);
        } catch (Exception ex) {
            throw new FailedRateAccessException(FAILED_ACCESS_MESSAGE, List.of(new ErrorResponse(ErrorType.ERROR, FAILED_ACCESS_MESSAGE)));
        }


    }

    @Override
    public Map<String, ExternalRateDto> getLastThirty(List<Pair<String, String>> currencyPairs) {
        String symbolParam = createSymbolParam(currencyPairs);
        String apiKey = this.twelvedataProps.getKey();

        try {
            return this.rateClient.get(symbolParam, DAILY_INTERVAL_NAME, LocalDate.now().minusDays(30), LocalDate.now().plusDays(1), apiKey);
        } catch (Exception ex) {
            throw new FailedRateAccessException(FAILED_ACCESS_MESSAGE, List.of(new ErrorResponse(ErrorType.ERROR, FAILED_ACCESS_MESSAGE)));
        }
    }


    private String createSymbolParam(List<Pair<String, String>> pairs) {


        StringBuilder stringBuilder = new StringBuilder();

        boolean isNeedComma = false;

        for (Pair<String, String> pair : pairs) {
            if (isNeedComma) {
                stringBuilder.append(",");
            } else {
                isNeedComma = true;
            }

            stringBuilder.append(makeStrPair(pair));
        }


        return stringBuilder.toString();
    }

    private String makeStrPair(Pair<String, String> pair) {
        StringBuilder strPair = new StringBuilder(pair.getFirst());
        strPair.append("/");
        strPair.append(pair.getSecond());

        return strPair.toString();
    }
}
