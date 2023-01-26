package com.compilit.recipes.core;

import static com.compilit.recipes.core.testutil.RecipeFactory.createRecipeDto;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.compilit.recipes.core.api.CreateRecipeCommand;
import com.compilit.recipes.core.api.IngredientDto;
import com.compilit.recipes.core.api.LoggedInUserDto;
import com.compilit.recipes.core.api.PersistencePort;
import com.compilit.recipes.core.api.RetrievalPort;
import com.compilit.recipes.core.exceptions.UnknownIngredientException;
import com.compilit.recipes.entities.Authority;
import com.compilit.recipes.entities.Category;
import com.compilit.recipes.entities.Ingredient;
import com.compilit.recipes.entities.Recipe;
import com.compilit.recipes.entities.RecipeAppUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CreateRecipeCommandHandlerTest {

  private final PersistencePort<Recipe> recipePersistencePort = Mockito.mock(PersistencePort.class);
  private final RetrievalPort<RecipeAppUser, String> recipeAppUserRetrievalPort = Mockito.mock(RetrievalPort.class);
  private final RetrievalPort<Ingredient, String> ingredientStringRetrievalPort = Mockito.mock(RetrievalPort.class);
  private final CreateRecipeCommandHandler createRecipeCommandHandler = new CreateRecipeCommandHandler(recipePersistencePort, recipeAppUserRetrievalPort, ingredientStringRetrievalPort);

  @Test
  void handle_validInput_shouldCreateRecipe() {
    var currentUser = new RecipeAppUser("test", "test", List.of(new Authority("USER")), true, true, true, true, new ArrayList<>());
    var currentUserDto = new LoggedInUserDto("test");
    var ingredient = new Ingredient("milk", Category.VEGETARIAN);
    when(ingredientStringRetrievalPort.findBy(anyString())).thenReturn(Optional.of(ingredient));
    when(recipeAppUserRetrievalPort.findBy(anyString())).thenReturn(Optional.of(currentUser));
    when(recipePersistencePort.persist(any(Recipe.class))).thenReturn(Recipe.builder().build());
    createRecipeCommandHandler.handle(new CreateRecipeCommand(createRecipeDto(2), currentUserDto));
    verify(recipePersistencePort).persist(any(Recipe.class));
  }

  @Test
  void handle_invalidInput_shouldThrowException() {
    var currentUser = new RecipeAppUser("test", "test", List.of(new Authority("USER")), true, true, true, true, new ArrayList<>());
    var currentUserDto = new LoggedInUserDto("test");
    when(recipeAppUserRetrievalPort.findBy(anyString())).thenReturn(Optional.of(currentUser));
    when(recipePersistencePort.persist(any(Recipe.class))).thenReturn(Recipe.builder().build());
    assertThatThrownBy(() -> createRecipeCommandHandler.handle(new CreateRecipeCommand(createRecipeDto(2), currentUserDto)))
      .isInstanceOf(UnknownIngredientException.class);
  }
}