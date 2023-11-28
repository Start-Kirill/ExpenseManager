package com.testtask.expensemanager.core.dtos;

import com.testtask.expensemanager.core.enums.ExpenseCategory;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
public class LimitUpdateDto {

    private BigDecimal limitSum;

    private BigDecimal reminder;

    private String currency_name;

    private ExpenseCategory expenseCategory;

    public LimitUpdateDto() {
    }

    public LimitUpdateDto(BigDecimal limitSum, BigDecimal reminder, String currency_name, ExpenseCategory expenseCategory) {
        this.limitSum = limitSum;
        this.reminder = reminder;
        this.currency_name = currency_name;
        this.expenseCategory = expenseCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LimitUpdateDto that = (LimitUpdateDto) o;
        return Objects.equals(limitSum, that.limitSum) && Objects.equals(reminder, that.reminder) && Objects.equals(currency_name, that.currency_name) && expenseCategory == that.expenseCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(limitSum, reminder, currency_name, expenseCategory);
    }

    @Override
    public String toString() {
        return "LimitUpdateDto{" +
                "limitSum=" + limitSum +
                ", reminder=" + reminder +
                ", currency_name='" + currency_name + '\'' +
                ", expenseCategory=" + expenseCategory +
                '}';
    }
}
