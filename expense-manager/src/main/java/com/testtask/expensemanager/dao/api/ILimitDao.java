package com.testtask.expensemanager.dao.api;

import com.testtask.expensemanager.dao.entyties.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ILimitDao extends JpaRepository<Limit, UUID> {
}
