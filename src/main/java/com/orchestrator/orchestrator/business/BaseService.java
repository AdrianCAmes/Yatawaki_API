package com.orchestrator.orchestrator.business;

import java.util.List;

public interface BaseService<T, I> {
    T create(T entity);
    T findById(I id);
    List<T> findAll();
    T change(T entity);
    T update(T entity) throws IllegalAccessException;
    T removeById(I id);
}
