package com.testtask.expensemanager.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.testtask.expensemanager.core.converters.json.StringToBigDecimalWithRoundingConverter;
import com.testtask.expensemanager.core.enums.ExpenseCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class TransactionCreateDto {

    @JsonDeserialize(converter = StringToBigDecimalWithRoundingConverter.class)
    @JsonProperty(value = "trans_sum")
    private BigDecimal transSum;

    @JsonProperty(value = "currency_shortname")
    private String currencyName;

    @JsonProperty(value = "expense_category")
    private ExpenseCategory expenseCategory;

    @JsonProperty(value = "account_from")
    private String accountFrom;

    @JsonProperty(value = "account_to")
    private String accountTo;


    public TransactionCreateDto(BigDecimal transSum, String currencyName, ExpenseCategory expenseCategory, String accountFrom, String accountTo) {
        this.transSum = transSum;
        this.currencyName = currencyName;
        this.expenseCategory = expenseCategory;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionCreateDto that = (TransactionCreateDto) o;
        return Objects.equals(transSum, that.transSum) && Objects.equals(currencyName, that.currencyName) && expenseCategory == that.expenseCategory && Objects.equals(accountFrom, that.accountFrom) && Objects.equals(accountTo, that.accountTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transSum, currencyName, expenseCategory, accountFrom, accountTo);
    }

    @Override
    public String toString() {
        return "TransactionCreateDto{" +
                "transSum=" + transSum +
                ", currencyName='" + currencyName + '\'' +
                ", expenseCategory=" + expenseCategory +
                ", accountFrom='" + accountFrom + '\'' +
                ", accountTo='" + accountTo + '\'' +
                '}';
    }
}
