package com.compilit.recipes.persistence;

import com.compilit.recipes.core.api.PersistencePort;
import com.compilit.recipes.core.api.RecipeRetrievalPort;
import com.compilit.recipes.entities.Category;
import com.compilit.recipes.entities.Recipe;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class RecipeDatabaseAdapter implements PersistencePort<Recipe>, RecipeRetrievalPort {
  private final RecipeRepository recipeRepository;
  @Override
  public Recipe persist(Recipe entity) {
    return recipeRepository.save(entity);
  }

  @Override
  public List<Recipe> retrieveByFilters(Category category,
                                        String includingIngredient,
                                        String excludingIngredient,
                                        Integer serves) {
    return recipeRepository.findAllByFilters(category, includingIngredient, excludingIngredient, serves);
  }
  @Override
  public Optional<Recipe> findBy(Long id) {
    return recipeRepository.findById(id);
  }

}
