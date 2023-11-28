package com.testtask.expensemanager.core.dtos;

import com.testtask.expensemanager.core.enums.ExpenseCategory;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
public class LimitCreateDto {

    private BigDecimal limitSum;

    private String currency_name;

    private ExpenseCategory expenseCategory;

    public LimitCreateDto() {
    }

    public LimitCreateDto(BigDecimal limitSum, String currency_name, ExpenseCategory expenseCategory) {
        this.limitSum = limitSum;
        this.currency_name = currency_name;
        this.expenseCategory = expenseCategory;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LimitCreateDto that = (LimitCreateDto) o;
        return Objects.equals(limitSum, that.limitSum) && Objects.equals(currency_name, that.currency_name) && expenseCategory == that.expenseCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(limitSum, currency_name, expenseCategory);
    }

    @Override
    public String toString() {
        return "LimitCreateDto{" +
                "limitSum=" + limitSum +
                ", currency_name='" + currency_name + '\'' +
                ", expenseCategory=" + expenseCategory +
                '}';
    }
}
