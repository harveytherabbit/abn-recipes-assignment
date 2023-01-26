package com.compilit.recipes.core.api;

import com.compilit.mediator.api.Command;

public record CreateIngredientCommand(IngredientDto ingredientDto) implements Command<Void> {
}
