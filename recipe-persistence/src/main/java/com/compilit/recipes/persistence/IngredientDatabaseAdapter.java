package com.compilit.recipes.persistence;

import com.compilit.recipes.core.api.PersistencePort;
import com.compilit.recipes.core.api.RetrievalPort;
import com.compilit.recipes.entities.Ingredient;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class IngredientDatabaseAdapter implements RetrievalPort<Ingredient, String>, PersistencePort<Ingredient> {

  private final IngredientRepository ingredientRepository;
  @Override
  public Optional<Ingredient> findBy(String name) {
    return ingredientRepository.findIngredientByName(name);
  }

  @Override
  public Ingredient persist(Ingredient entity) {
    return ingredientRepository.save(entity);
  }
}
