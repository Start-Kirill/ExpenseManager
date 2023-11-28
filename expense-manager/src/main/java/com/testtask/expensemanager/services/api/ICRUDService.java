package com.testtask.expensemanager.services.api;

import java.util.List;
import java.util.UUID;

public interface ICRUDService<E, D> {

    E get(UUID uuid);

    List<E> get();

    E save(D d);
}
