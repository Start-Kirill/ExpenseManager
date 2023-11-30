package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.LimitCreateDto;
import com.testtask.expensemanager.core.enums.ErrorType;
import com.testtask.expensemanager.core.enums.ExpenseCategory;
import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.dao.api.ILimitDao;
import com.testtask.expensemanager.dao.entyties.Limit;
import com.testtask.expensemanager.services.api.ICurrencyService;
import com.testtask.expensemanager.services.api.ILimitService;
import com.testtask.expensemanager.services.exceptions.FailedSaveLimitException;
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

    private static final String LIMIT_NOT_EXIST_MESSAGE = "Limit with such uuid does not exist";

    private static final String FAILED_SAVE_MESSAGE = "Saving limit failed";

    private static final String NO_ANY_LIMIT_MESSAGE = "There is no any limit in database";

    private static final String LIMIT_CURRENCY_NAME = "USD";

    private static final String LIMIT_SUM_FIELD_NANE = "limit_sum";

    private static final String EXPENSE_CATEGORY_FIELD_NANE = "expense_category";

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
        } catch (NoSuchElementException ex) {
            throw new SuchLimitNotExistsException(LIMIT_NOT_EXIST_MESSAGE, List.of(new ErrorResponse(ErrorType.ERROR, LIMIT_NOT_EXIST_MESSAGE)));
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
            throw new FailedSaveLimitException(FAILED_SAVE_MESSAGE, List.of(new ErrorResponse(ErrorType.ERROR, FAILED_SAVE_MESSAGE)));
        }

    }

    @Transactional(readOnly = true)
    @Override
    public Limit getUpToDate(ExpenseCategory expenseCategory) {
        try {
            return this.limitDao.findTopByExpenseCategoryOrderByDateTimeCreateDesc(expenseCategory).orElseThrow();
        } catch (NoSuchElementException ex) {
            throw new SuchLimitNotExistsException(NO_ANY_LIMIT_MESSAGE, List.of(new ErrorResponse(ErrorType.ERROR, NO_ANY_LIMIT_MESSAGE)));
        }
    }

    private void validate(LimitCreateDto limitCreateDto) {
        HashMap<String, String> errors = new HashMap<>();

        BigDecimal limitSum = limitCreateDto.getLimitSum();

        if (limitSum == null) {
            errors.put(LIMIT_SUM_FIELD_NANE, "Field should be filled");
        } else if (limitSum.compareTo(BigDecimal.ZERO) < 0) {
            errors.put(LIMIT_SUM_FIELD_NANE, "Value must be positive");
        }

        ExpenseCategory expenseCategory = limitCreateDto.getExpenseCategory();

        if (expenseCategory == null) {
            errors.put(EXPENSE_CATEGORY_FIELD_NANE, "Field should be filled");
        }

        if (!errors.isEmpty()) {
            throw new InvalidLimitBodyException(errors);
        }

    }

}
