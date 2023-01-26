package com.compilit.recipes.core.api;

import java.util.List;

public record RecipeDto(String name, String instructions, List<IngredientDto> ingredients, int serves) {
}
