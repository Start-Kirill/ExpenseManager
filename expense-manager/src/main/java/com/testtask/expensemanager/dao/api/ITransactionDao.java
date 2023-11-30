package com.testtask.expensemanager.dao.api;

import com.testtask.expensemanager.core.enums.ExpenseCategory;
import com.testtask.expensemanager.dao.entyties.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ITransactionDao extends JpaRepository<Transaction, UUID> {

    List<Transaction> findAllByIsExceeded(boolean isExceeded);

    @Query(nativeQuery = true, value = "SELECT SUM(trans_sum_in_usd) FROM app.transactions INNER JOIN app.limits ON limit_uuid=app.limits.uuid " +
            "WHERE limit_uuid = (SELECT uuid FROM app.limits WHERE expense_category = :expenseCategory ORDER BY datetime_create DESC LIMIT 1) GROUP BY limit_uuid")
    BigDecimal findActualExpense(@Param("expenseCategory") String expenseCategory);
}
