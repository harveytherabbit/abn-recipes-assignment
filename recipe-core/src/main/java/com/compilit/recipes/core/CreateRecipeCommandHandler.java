package com.compilit.recipes.core;

import com.compilit.mediator.api.CommandHandler;
import com.compilit.recipes.core.api.CreateRecipeCommand;
import com.compilit.recipes.core.api.IngredientDto;
import com.compilit.recipes.core.api.PersistencePort;
import com.compilit.recipes.core.api.RetrievalPort;
import com.compilit.recipes.core.exceptions.UnknownIngredientException;
import com.compilit.recipes.core.exceptions.UnknownUserException;
import com.compilit.recipes.entities.Ingredient;
import com.compilit.recipes.entities.IngredientSpecifier;
import com.compilit.recipes.entities.Recipe;
import com.compilit.recipes.entities.RecipeAppUser;
import com.compilit.recipes.entities.Unit;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class CreateRecipeCommandHandler implements CommandHandler<CreateRecipeCommand, Long> {

  private final PersistencePort<Recipe> recipePersistencePort;
  private final RetrievalPort<RecipeAppUser, String> recipeAppUserRetrievalPort;
  private final RetrievalPort<Ingredient, String> ingredientRetrievalPort;

  @Override
  public Long handle(CreateRecipeCommand command) {
    log.info("Creating new recipe {}", command.recipeDto());
    var recipeDto = command.recipeDto();
    var username = command.currentUser().username();
    var currentUser = recipeAppUserRetrievalPort.findBy(username).orElseThrow(() -> new UnknownUserException(username));
    var recipe = new Recipe(
      currentUser,
      recipeDto.name(),
      recipeDto.instructions(),
      recipeDto.serves()
    );
    var ingredients = recipeDto.ingredients()
                               .stream()
                               .map(ingredientDtoToIngredientSpecifier(recipe))
                               .toList();
    recipe.addIngredients(ingredients);
    return recipePersistencePort.persist(recipe).getId();
  }

  private Function<IngredientDto, IngredientSpecifier> ingredientDtoToIngredientSpecifier(Recipe recipe) {
    return ingredientDto -> {
      var ingredientName = ingredientDto.name();
      var ingredient = ingredientRetrievalPort.findBy(ingredientName)
                                              .orElseThrow(() -> new UnknownIngredientException(ingredientName));
      return new IngredientSpecifier(
        ingredient,
        ingredientDto.amount(),
        Unit.valueOf(
          ingredientDto.unit()),
        recipe
      );
    };
  }
}
