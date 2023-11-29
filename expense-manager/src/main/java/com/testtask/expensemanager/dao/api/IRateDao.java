package com.testtask.expensemanager.dao.api;

import com.testtask.expensemanager.dao.entyties.Limit;
import com.testtask.expensemanager.dao.entyties.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface IRateDao extends JpaRepository<Rate, UUID> {
    Rate findTopByOrderByDateDesc();
}
