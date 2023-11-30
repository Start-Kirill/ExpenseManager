package com.testtask.expensemanager.dao.api;

import com.testtask.expensemanager.core.enums.ExpenseCategory;
import com.testtask.expensemanager.dao.entyties.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ILimitDao extends JpaRepository<Limit, UUID> {

    Optional<Limit> findTopByExpenseCategoryOrderByDateTimeCreateDesc(ExpenseCategory expenseCategory);

}
