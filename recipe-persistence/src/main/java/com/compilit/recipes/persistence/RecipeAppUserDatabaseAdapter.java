package com.compilit.recipes.persistence;

import com.compilit.recipes.core.api.PersistencePort;
import com.compilit.recipes.core.api.RetrievalPort;
import com.compilit.recipes.entities.RecipeAppUser;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class RecipeAppUserDatabaseAdapter implements PersistencePort<RecipeAppUser>, RetrievalPort<RecipeAppUser, String> {

  private final RecipeAppUserRepository recipeAppUserRepository;
  @Override
  public RecipeAppUser persist(RecipeAppUser entity) {
    return recipeAppUserRepository.save(entity);
  }

  @Override
  public Optional<RecipeAppUser> findBy(String username) {
    return recipeAppUserRepository.findById(username);
  }
}
