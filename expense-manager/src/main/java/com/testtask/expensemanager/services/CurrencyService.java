package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.CurrencyCreateDto;
import com.testtask.expensemanager.core.dtos.ExternalRateDto;
import com.testtask.expensemanager.core.dtos.RateCreateDto;
import com.testtask.expensemanager.core.enums.ErrorType;
import com.testtask.expensemanager.core.enums.RateStatus;
import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.core.utils.CustomConverter;
import com.testtask.expensemanager.dao.api.ICurrencyDao;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.services.api.ICurrencyService;
import com.testtask.expensemanager.services.api.IExternalRateService;
import com.testtask.expensemanager.services.api.IRateService;
import com.testtask.expensemanager.services.exceptions.FailedSaveCurrencyException;
import com.testtask.expensemanager.services.exceptions.InvalidCurrencyBodyException;
import com.testtask.expensemanager.services.exceptions.SuchCurrencyNotExistsException;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CurrencyService implements ICurrencyService {

    private static final String CURRENCY_NOT_EXIST_MESSAGE = "Currency with such name does not exist";

    private static final String CURRENCY_NOT_STORED_MESSAGE = "Currency with such name is note stored in database";

    private static final String FAILED_SAVE_MESSAGE = "Saving currency failed";

    private static final String NAME_FIELD_NAME = "name";

    private static final String DEFAULT_CURRENCY_NAME = "USD";

    private static final String EXTERNAL_OK_FILED_VALUE = "ok";

    private final ICurrencyDao currencyDao;

    private final IExternalRateService externalRateService;

    private final IRateService rateService;

    private final ConversionService conversionService;

    public CurrencyService(ICurrencyDao currencyDao,
                           ConversionService conversionService,
                           IExternalRateService externalRateService,
                           @Lazy IRateService rateService) {
        this.currencyDao = currencyDao;
        this.conversionService = conversionService;
        this.externalRateService = externalRateService;
        this.rateService = rateService;
    }

    @Transactional
    @Override
    public Currency save(CurrencyCreateDto currencyCreateDto) {

        validate(currencyCreateDto);

        Currency currency = this.conversionService.convert(currencyCreateDto, Currency.class);

        currency.setUuid(UUID.randomUUID());


        try {
            List<Pair<String, String>> pairs = createPairsForNewCurrency(currencyCreateDto.getName());

            Currency save = this.currencyDao.save(currency);

            Map<String, ExternalRateDto> lastThirty = this.externalRateService.getLastThirty(pairs);

            List<RateCreateDto> rateCreateDtos = CustomConverter.convert(lastThirty, RateStatus.DONE);

            this.rateService.saveAll(rateCreateDtos);

            return save;
        } catch (Exception ex) {
            throw new FailedSaveCurrencyException(FAILED_SAVE_MESSAGE, List.of(new ErrorResponse(ErrorType.ERROR, FAILED_SAVE_MESSAGE)));
        }

    }

    @Transactional(readOnly = true)
    @Override
    public Currency get(UUID uuid) {
        try {
            return this.currencyDao.findById(uuid).orElseThrow();
        } catch (NoSuchElementException ex) {
            throw new SuchCurrencyNotExistsException(CURRENCY_NOT_EXIST_MESSAGE, List.of(new ErrorResponse(ErrorType.ERROR, CURRENCY_NOT_EXIST_MESSAGE)));
        }

    }

    @Transactional(readOnly = true)
    @Override
    public Currency get(String name) {
        try {
            return this.currencyDao.findByName(name).orElseThrow();
        } catch (NoSuchElementException ex) {
            throw new SuchCurrencyNotExistsException(CURRENCY_NOT_STORED_MESSAGE, List.of(new ErrorResponse(ErrorType.ERROR, CURRENCY_NOT_STORED_MESSAGE)));
        }

    }

    @Override
    public List<Pair<String, String>> getCurrencyPairs() {
        List<Currency> currencies = this.get();
        return createAllPairs(currencies);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Currency> get() {
        return this.currencyDao.findAll();
    }


    private void validate(CurrencyCreateDto currencyCreateDto) {

        Map<String, String> errors = new HashMap<>();

        String name = currencyCreateDto.getName();
        if (name == null || name.isEmpty()) {

            errors.put(NAME_FIELD_NAME, "Filed should be filled");

        } else if (this.currencyDao.existsByName(name)) {

            errors.put(NAME_FIELD_NAME, "Such currency already exists");

        } else if (!checkIfExists(name)) {

            errors.put(NAME_FIELD_NAME, "Such currency is not available");

        }

        if (!errors.isEmpty()) {
            throw new InvalidCurrencyBodyException(errors);
        }
    }

    private boolean checkIfExists(String currencyName) {
        List<Pair<String, String>> pairs = List.of(Pair.of(DEFAULT_CURRENCY_NAME, currencyName), Pair.of(currencyName, DEFAULT_CURRENCY_NAME));
        Map<String, ExternalRateDto> lastThirty = this.externalRateService.getLastThirty(pairs);
        Optional<ExternalRateDto> first = lastThirty.values().stream().filter(e -> e.getStatus().equalsIgnoreCase(EXTERNAL_OK_FILED_VALUE)).findFirst();
        return first.isPresent();
    }

    private List<Pair<String, String>> createPairsForNewCurrency(String currencyName) {
        List<Currency> currencies = this.get();
        List<Pair<String, String>> pairs = new ArrayList<>();
        currencies.forEach(c -> {
            pairs.add(Pair.of(currencyName, c.getName()));
            pairs.add(Pair.of(c.getName(), currencyName));
        });
        return pairs;
    }

    private List<Pair<String, String>> createAllPairs(List<Currency> currencies) {
        List<Pair<String, String>> pairs = new ArrayList<>();

        int index = 0;
        int currentIndex = 0;

        while (index < currencies.size() - 1) {
            currentIndex = index + 1;
            while (currentIndex < currencies.size()) {
                Currency firstCurrency = currencies.get(index);
                Currency secondCurrency = currencies.get(currentIndex);
                pairs.add(Pair.of(firstCurrency.getName(), secondCurrency.getName()));
                pairs.add(Pair.of(secondCurrency.getName(), firstCurrency.getName()));
                currentIndex++;
            }
            index++;
        }

        return pairs;
    }
}
