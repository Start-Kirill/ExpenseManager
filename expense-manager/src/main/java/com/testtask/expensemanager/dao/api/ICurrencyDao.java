package com.testtask.expensemanager.dao.api;

import com.testtask.expensemanager.dao.entyties.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ICurrencyDao extends JpaRepository<Currency, UUID> {

    Optional<Currency> findByName(String name);

    boolean existsByName(String name);

}
