package com.compilit.recipes.core.api;

import java.util.Optional;

public interface RetrievalPort<T, ID> {
    Optional<T> findBy(ID id);
}
