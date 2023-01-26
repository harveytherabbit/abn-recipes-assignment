package com.compilit.recipes.core;

import com.compilit.mediator.api.QueryHandler;
import com.compilit.recipes.core.api.FindRecipesByFiltersQuery;
import com.compilit.recipes.core.api.IngredientDto;
import com.compilit.recipes.core.api.RecipeDto;
import com.compilit.recipes.core.api.RecipeRetrievalPort;
import com.compilit.recipes.core.api.RetrievalPort;
import com.compilit.recipes.core.exceptions.UnknownCategoryException;
import com.compilit.recipes.core.exceptions.UnknownIngredientException;
import com.compilit.recipes.entities.Category;
import com.compilit.recipes.entities.Ingredient;
import com.compilit.recipes.entities.IngredientSpecifier;
import com.compilit.recipes.entities.Recipe;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class FindRecipesByFiltersQueryHandler implements QueryHandler<FindRecipesByFiltersQuery, List<RecipeDto>> {

  private final RecipeRetrievalPort recipeRetrievalPort;

  @Override
  public List<RecipeDto> handle(FindRecipesByFiltersQuery query) {
    log.info("Searching for recipes new user {}", query);
    var category = Category.get(query.category())
                           .orElse(null);
    var included = query.includingIngredient();
    var excluded = query.excludingIngredient();
    var serves = query.serves();
    return recipeRetrievalPort.retrieveByFilters(category, included, excluded, serves)
                              .stream()
                              .map(recipeToRecipeDto())
                              .collect(Collectors.toList());
  }

  private static Function<Recipe, RecipeDto> recipeToRecipeDto() {
    return recipe -> new RecipeDto(
      recipe.getName(),
      recipe.getInstructions(),
      recipe.getIngredientSpecifiers()
            .stream()
            .map(ingredientsToIngredientDto())
            .toList(),
      recipe.getServes()
    );
  }

  private static Function<IngredientSpecifier, IngredientDto> ingredientsToIngredientDto() {
    return ingredientSpecifier -> new IngredientDto(
      ingredientSpecifier.getIngredient().getName(),
      ingredientSpecifier.getIngredient().getCategory().name(),
      ingredientSpecifier.getAmount(),
      ingredientSpecifier.getUnit().name()
    );
  }

}
