package com.testtask.expensemanager.dao.api;

import com.testtask.expensemanager.dao.entyties.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ITransactionDao extends JpaRepository<Transaction, UUID> {

    List<Transaction> findAllByIsExceeded();
}
