package com.testtask.expensemanager.dao.entyties;

import com.testtask.expensemanager.core.enums.ExpenseCategory;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "limits")
public class Limit implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    private UUID uuid;

    @Column(name = "datetime")
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "expense_category")
    private ExpenseCategory expenseCategory;

    @Column(name = "limit_sum")
    private BigDecimal limitSum;

    @ManyToOne
    @JoinColumn(name = "currency_uuid")
    private Currency currency;

    public Limit() {
    }

    public Limit(UUID uuid, LocalDateTime dateTime, ExpenseCategory expenseCategory, BigDecimal limitSum, Currency currency) {
        this.uuid = uuid;
        this.dateTime = dateTime;
        this.expenseCategory = expenseCategory;
        this.limitSum = limitSum;
        this.currency = currency;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public BigDecimal getLimitSum() {
        return limitSum;
    }

    public void setLimitSum(BigDecimal limitSum) {
        this.limitSum = limitSum;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Limit limit = (Limit) o;
        return Objects.equals(uuid, limit.uuid) && Objects.equals(dateTime, limit.dateTime) && expenseCategory == limit.expenseCategory && Objects.equals(limitSum, limit.limitSum) && Objects.equals(currency, limit.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dateTime, expenseCategory, limitSum, currency);
    }

    @Override
    public String toString() {
        return "Limit{" +
                "uuid=" + uuid +
                ", dateTime=" + dateTime +
                ", expenseCategory=" + expenseCategory +
                ", limitSum=" + limitSum +
                ", currency=" + currency +
                '}';
    }
}
