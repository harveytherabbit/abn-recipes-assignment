package com.compilit.recipes.core.api;

import com.compilit.recipes.entities.Category;
import com.compilit.recipes.entities.Ingredient;
import com.compilit.recipes.entities.IngredientSpecifier;
import com.compilit.recipes.entities.Recipe;
import java.util.List;

public interface RecipeRetrievalPort extends RetrievalPort<Recipe, Long> {
  List<Recipe> retrieveByFilters(Category category,
                                 String includingIngredient,
                                 String excludingIngredient,
                                 Integer serves);
}
