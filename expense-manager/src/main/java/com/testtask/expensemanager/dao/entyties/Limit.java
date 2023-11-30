package com.testtask.expensemanager.dao.entyties;

import com.testtask.expensemanager.core.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "limits")
public class Limit implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "expense_category")
    private ExpenseCategory expenseCategory;

    @Column(name = "limit_sum")
    private BigDecimal limitSum;


    @ManyToOne
    @JoinColumn(name = "currency_uuid")
    private Currency currency;

    @Column(name = "datetime_create")
    private LocalDateTime dateTimeCreate;


    public Limit(UUID uuid, ExpenseCategory expenseCategory, BigDecimal limitSum, Currency currency, LocalDateTime dateTimeCreate) {
        this.uuid = uuid;
        this.expenseCategory = expenseCategory;
        this.limitSum = limitSum;
        this.currency = currency;
        this.dateTimeCreate = dateTimeCreate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Limit limit = (Limit) o;
        return Objects.equals(uuid, limit.uuid) && expenseCategory == limit.expenseCategory && Objects.equals(limitSum, limit.limitSum) && Objects.equals(currency, limit.currency) && Objects.equals(dateTimeCreate, limit.dateTimeCreate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, expenseCategory, limitSum, currency, dateTimeCreate);
    }

    @Override
    public String toString() {
        return "Limit{" +
                "uuid=" + uuid +
                ", expenseCategory=" + expenseCategory +
                ", limitSum=" + limitSum +
                ", currency=" + currency +
                ", dateTimeCreate=" + dateTimeCreate +
                '}';
    }
}
