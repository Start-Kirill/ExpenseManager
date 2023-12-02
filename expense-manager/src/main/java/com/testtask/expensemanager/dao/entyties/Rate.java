package com.testtask.expensemanager.dao.entyties;

import com.testtask.expensemanager.core.enums.RateStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
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

    @Serial
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


    private LocalDateTime datetime;

    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @Version
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;

    @Enumerated(EnumType.STRING)
    private RateStatus status;

    private Long attempt;


    public Rate(UUID uuid, Currency firstCurrency, Currency secondCurrency, BigDecimal value, LocalDateTime datetime, LocalDateTime dtCreate, LocalDateTime dtUpdate, RateStatus status, Long attempt) {
        this.uuid = uuid;
        this.firstCurrency = firstCurrency;
        this.secondCurrency = secondCurrency;
        this.value = value;
        this.datetime = datetime;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.status = status;
        this.attempt = attempt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate = (Rate) o;
        return Objects.equals(uuid, rate.uuid) && Objects.equals(firstCurrency, rate.firstCurrency) && Objects.equals(secondCurrency, rate.secondCurrency) && Objects.equals(value, rate.value) && Objects.equals(datetime, rate.datetime) && Objects.equals(dtCreate, rate.dtCreate) && Objects.equals(dtUpdate, rate.dtUpdate) && status == rate.status && Objects.equals(attempt, rate.attempt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, firstCurrency, secondCurrency, value, datetime, dtCreate, dtUpdate, status, attempt);
    }

    @Override
    public String toString() {
        return "Rate{" +
                "uuid=" + uuid +
                ", firstCurrency=" + firstCurrency +
                ", secondCurrency=" + secondCurrency +
                ", value=" + value +
                ", dateTime=" + datetime +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", status=" + status +
                ", attempt=" + attempt +
                '}';
    }
}
