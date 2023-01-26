package com.compilit.recipes.core.testutil;

import com.compilit.recipes.core.api.IngredientDto;

public final class IngredientSpecificationFactory {

  private IngredientSpecificationFactory() {}

  public static IngredientDto createIngredientSpecificationDto() {
    return new IngredientDto("milk", "VEGETARIAN", 1d, "LITER");
  }
}
