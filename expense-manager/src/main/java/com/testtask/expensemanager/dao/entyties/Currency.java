package com.testtask.expensemanager.dao.entyties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Currency implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    private UUID uuid;

    @Column
    private String name;

    public Currency() {
    }

    public Currency(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(uuid, currency.uuid) && Objects.equals(name, currency.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }
}
