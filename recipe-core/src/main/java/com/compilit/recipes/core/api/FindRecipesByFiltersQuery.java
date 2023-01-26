package com.compilit.recipes.core.api;

import com.compilit.mediator.api.Query;
import java.util.List;

public record FindRecipesByFiltersQuery(String category,
                                        String includingIngredient,
                                        String excludingIngredient,
                                        Integer serves, LoggedInUserDto currentUser) implements Query<List<RecipeDto>> {
}
