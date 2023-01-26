package com.compilit.recipes.core.api;

public interface PersistencePort<T> {

    T persist(T entity);
}
