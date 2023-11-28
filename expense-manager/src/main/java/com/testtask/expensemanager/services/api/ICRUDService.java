package com.testtask.expensemanager.services.api;

import java.util.List;

public interface ICRUDService<E, D> {
    List<E> get();

    E save(D d);
}
