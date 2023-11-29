package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.LimitCreateDto;
import com.testtask.expensemanager.core.enums.ErrorType;
import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.dao.api.ILimitDao;
import com.testtask.expensemanager.dao.entyties.Limit;
import com.testtask.expensemanager.services.api.ICurrencyService;
import com.testtask.expensemanager.services.api.ILimitService;
import com.testtask.expensemanager.services.exceptions.InvalidLimitBodyException;
import com.testtask.expensemanager.services.exceptions.SuchLimitNotExistsException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class LimitService implements ILimitService {

    private static final String LIMIT_CURRENCY_NAME = "USD";

    private static final String LIMIT_SUM_FILED_NANE = "limit_sum";

    private final ILimitDao limitDao;

    private final ConversionService conversionService;

    private final ICurrencyService currencyService;

    public LimitService(ILimitDao limitDao,
                        ConversionService conversionService,
                        ICurrencyService currencyService) {
        this.limitDao = limitDao;
        this.conversionService = conversionService;
        this.currencyService = currencyService;
    }

    @Transactional(readOnly = true)
    @Override
    public Limit get(UUID uuid) {
        try {
            return this.limitDao.findById(uuid).orElseThrow();
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Limit> get() {
        return this.limitDao.findAll();
    }

    @Transactional
    @Override
    public Limit save(LimitCreateDto limitCreateDto) {

        validate(limitCreateDto);

        Limit limit = this.conversionService.convert(limitCreateDto, Limit.class);
        limit.setCurrency(this.currencyService.get(LIMIT_CURRENCY_NAME));
        limit.setDateTimeCreate(LocalDateTime.now());
        limit.setUuid(UUID.randomUUID());

        try {
            return this.limitDao.save(limit);
        } catch (Exception ex) {
            throw new RuntimeException();
        }

    }

    //    TODO
    @Override
    public BigDecimal getReminder(UUID uuid) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Limit getUpToDate() {
        try {
            return this.limitDao.findTopByOrderByDateTimeCreateDesc().orElseThrow();
        } catch (NoSuchElementException ex) {
            throw new SuchLimitNotExistsException(List.of(new ErrorResponse(ErrorType.ERROR, "There is no any limit in database")));
        }
    }

    private void validate(LimitCreateDto limitCreateDto) {
        HashMap<String, String> errors = new HashMap<>();

        BigDecimal limitSum = limitCreateDto.getLimitSum();

        if(limitSum == null){
            errors.put(LIMIT_SUM_FILED_NANE, "Filed should be filled");
        }

        if (limitSum.compareTo(BigDecimal.ZERO) < 0) {
            errors.put(LIMIT_SUM_FILED_NANE, "Value must be positive");
        }

        if (!errors.isEmpty()) {
            throw new InvalidLimitBodyException(errors);
        }

    }

}
