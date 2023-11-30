package com.testtask.expensemanager.dao.entyties;

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
@Table(name = "currency_rates")
public class Rate implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "first_currency_uuid")
    private Currency firstCurrency;

    @ManyToOne
    @JoinColumn(name = "second_currency_uuid")
    private Currency secondCurrency;

    private BigDecimal value;

    private LocalDateTime date;


    public Rate(UUID uuid, Currency firstCurrency, Currency secondCurrency, BigDecimal value, LocalDateTime date) {
        this.uuid = uuid;
        this.firstCurrency = firstCurrency;
        this.secondCurrency = secondCurrency;
        this.value = value;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate = (Rate) o;
        return Objects.equals(uuid, rate.uuid) && Objects.equals(firstCurrency, rate.firstCurrency) && Objects.equals(secondCurrency, rate.secondCurrency) && Objects.equals(value, rate.value) && Objects.equals(date, rate.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, firstCurrency, secondCurrency, value, date);
    }

    @Override
    public String toString() {
        return "Rate{" +
                "uuid=" + uuid +
                ", firstCurrency=" + firstCurrency +
                ", secondCurrency=" + secondCurrency +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
