package com.compilit.recipes.core.testutil;

import static com.compilit.recipes.core.testutil.IngredientSpecificationFactory.createIngredientSpecificationDto;

import com.compilit.recipes.core.api.IngredientDto;
import com.compilit.recipes.core.api.RecipeDto;
import java.util.ArrayList;

public final class RecipeFactory {

  private RecipeFactory() {}

  public static RecipeDto createRecipeDto(int numOfIngredients) {
    var ingredients = new ArrayList<IngredientDto>();
    for (int counter = 0; counter < numOfIngredients; counter++) {
      ingredients.add(createIngredientSpecificationDto());
    }
    return new RecipeDto("test", "test instructions", ingredients, 4);
  }
}
