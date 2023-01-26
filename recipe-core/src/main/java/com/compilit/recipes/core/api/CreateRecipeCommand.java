package com.compilit.recipes.core.api;

import com.compilit.mediator.api.Command;

public record CreateRecipeCommand(RecipeDto recipeDto, LoggedInUserDto currentUser) implements Command<Long> {
}
