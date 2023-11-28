package com.testtask.expensemanager.dao.api;

import com.testtask.expensemanager.dao.entyties.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ITransactionDao extends JpaRepository<Transaction, UUID> {
}
