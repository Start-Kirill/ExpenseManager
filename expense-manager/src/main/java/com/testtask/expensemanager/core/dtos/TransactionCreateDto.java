package com.testtask.expensemanager.core.dtos;

import com.testtask.expensemanager.core.enums.ExpenseCategory;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class TransactionCreateDto {

    private BigDecimal transSum;

    private String currencyName;

    private ExpenseCategory expenseCategory;

    private String accountFrom;

    private String accountTo;

    private LocalDateTime dateTime;

    public TransactionCreateDto() {
    }

    public TransactionCreateDto(BigDecimal transSum, String currencyName, ExpenseCategory expenseCategory, String accountFrom, String accountTo, LocalDateTime dateTime) {
        this.transSum = transSum;
        this.currencyName = currencyName;
        this.expenseCategory = expenseCategory;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionCreateDto that = (TransactionCreateDto) o;
        return Objects.equals(transSum, that.transSum) && Objects.equals(currencyName, that.currencyName) && expenseCategory == that.expenseCategory && Objects.equals(accountFrom, that.accountFrom) && Objects.equals(accountTo, that.accountTo) && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transSum, currencyName, expenseCategory, accountFrom, accountTo, dateTime);
    }

    @Override
    public String toString() {
        return "TransactionCreateDto{" +
                "transSum=" + transSum +
                ", currencyName='" + currencyName + '\'' +
                ", expenseCategory=" + expenseCategory +
                ", accountFrom='" + accountFrom + '\'' +
                ", accountTo='" + accountTo + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
