package com.testtask.expensemanager.dao.entyties;

import com.testtask.expensemanager.core.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 42L;

    @Getter
    @Id
    private UUID uuid;

    @Getter
    @Column(name = "datetime")
    private LocalDateTime dateTime;

    @Getter
    @ManyToOne
    @JoinColumn(name = "currency_uuid")
    private Currency currency;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "expense_category")
    private ExpenseCategory expenseCategory;

    @Getter
    @Column(name = "account_from")
    private String accountFrom;

    @Getter
    @Column(name = "account_to")
    private String accountTo;

    @Getter
    @Column(name = "trans_sum")
    private BigDecimal transSum;

    @Getter
    @ManyToOne
    @JoinColumn(name = "limit_uuid")
    private Limit limit;

    @Column(name = "exceeded")
    private boolean isExceeded;

    public Transaction() {
    }

    public Transaction(UUID uuid, LocalDateTime dateTime, Currency currency, ExpenseCategory expenseCategory, String accountFrom, String accountTo, BigDecimal transSum, Limit limit, boolean isExceeded) {
        this.uuid = uuid;
        this.dateTime = dateTime;
        this.currency = currency;
        this.expenseCategory = expenseCategory;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.transSum = transSum;
        this.limit = limit;
        this.isExceeded = isExceeded;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public void setTransSum(BigDecimal transSum) {
        this.transSum = transSum;
    }

    public void setLimit(Limit limit) {
        this.limit = limit;
    }

    public boolean isExceeded() {
        return isExceeded;
    }

    public void setExceeded(boolean exceeded) {
        isExceeded = exceeded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return isExceeded == that.isExceeded && Objects.equals(uuid, that.uuid) && Objects.equals(dateTime, that.dateTime) && Objects.equals(currency, that.currency) && expenseCategory == that.expenseCategory && Objects.equals(accountFrom, that.accountFrom) && Objects.equals(accountTo, that.accountTo) && Objects.equals(transSum, that.transSum) && Objects.equals(limit, that.limit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dateTime, currency, expenseCategory, accountFrom, accountTo, transSum, limit, isExceeded);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "uuid=" + uuid +
                ", dateTime=" + dateTime +
                ", currency=" + currency +
                ", expenseCategory=" + expenseCategory +
                ", accountFrom='" + accountFrom + '\'' +
                ", accountTo='" + accountTo + '\'' +
                ", transSum=" + transSum +
                ", limit=" + limit +
                ", isExceeded=" + isExceeded +
                '}';
    }
}
