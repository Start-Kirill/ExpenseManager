package com.testtask.expensemanager.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.testtask.expensemanager.core.enums.ExpenseCategory;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class TransactionDto {

    private UUID uuid;

    @JsonProperty("datetime")
    private LocalDateTime dateTime;

    @JsonProperty("trans_sum")
    private BigDecimal transSum;

    @JsonProperty("currency_shortname")
    private String currencyName;

    @JsonProperty("expense_category")
    private ExpenseCategory expenseCategory;

    @JsonProperty("account_from")
    private String accountFrom;

    @JsonProperty("account_to")
    private String accountTo;

    @JsonProperty("limit_exceeded")
    private boolean limitExceeded;

    @JsonProperty("limit_sum")
    private BigDecimal limitSum;

    @JsonProperty("limit_datetime")
    private LocalDateTime limitDateTime;

    @JsonProperty("limit_currency")
    private String limitCurrencyName;

    public TransactionDto() {
    }

    public TransactionDto(UUID uuid, LocalDateTime dateTime, BigDecimal transSum, String currencyName, ExpenseCategory expenseCategory, String accountFrom, String accountTo, boolean limitExceeded, BigDecimal limitSum, LocalDateTime limitDateTime, String limitCurrencyName) {
        this.uuid = uuid;
        this.dateTime = dateTime;
        this.transSum = transSum;
        this.currencyName = currencyName;
        this.expenseCategory = expenseCategory;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.limitExceeded = limitExceeded;
        this.limitSum = limitSum;
        this.limitDateTime = limitDateTime;
        this.limitCurrencyName = limitCurrencyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDto that = (TransactionDto) o;
        return limitExceeded == that.limitExceeded && Objects.equals(uuid, that.uuid) && Objects.equals(dateTime, that.dateTime) && Objects.equals(transSum, that.transSum) && Objects.equals(currencyName, that.currencyName) && expenseCategory == that.expenseCategory && Objects.equals(accountFrom, that.accountFrom) && Objects.equals(accountTo, that.accountTo) && Objects.equals(limitSum, that.limitSum) && Objects.equals(limitDateTime, that.limitDateTime) && Objects.equals(limitCurrencyName, that.limitCurrencyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dateTime, transSum, currencyName, expenseCategory, accountFrom, accountTo, limitExceeded, limitSum, limitDateTime, limitCurrencyName);
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "uuid=" + uuid +
                ", dateTime=" + dateTime +
                ", transSum=" + transSum +
                ", currencyName='" + currencyName + '\'' +
                ", expenseCategory=" + expenseCategory +
                ", accountFrom='" + accountFrom + '\'' +
                ", accountTo='" + accountTo + '\'' +
                ", limitExceeded=" + limitExceeded +
                ", limitSum=" + limitSum +
                ", limitDateTime=" + limitDateTime +
                ", limitCurrencyName='" + limitCurrencyName + '\'' +
                '}';
    }
}
