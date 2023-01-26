package com.compilit.recipes.core;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.compilit.recipes.core.api.CreateIngredientCommand;
import com.compilit.recipes.core.api.IngredientDto;
import com.compilit.recipes.core.api.PersistencePort;
import com.compilit.recipes.core.exceptions.UnknownCategoryException;
import com.compilit.recipes.entities.Category;
import com.compilit.recipes.entities.Ingredient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateIngredientCommandHandlerTest {

  @Mock
  private PersistencePort<Ingredient> ingredientPersistencePort;
  @InjectMocks
  private CreateIngredientCommandHandler createIngredientCommandHandler;

  @Test
  void handle_validInput_shouldCreateNewIngredient() {
    createIngredientCommandHandler.handle(new CreateIngredientCommand(new IngredientDto("pork", Category.NON_VEG.name(), null, null)));
    verify(ingredientPersistencePort).persist(any(Ingredient.class));
  }

  @Test
  void handle_invalidInput_shouldThrowException() {
    assertThatThrownBy(() -> createIngredientCommandHandler.handle(new CreateIngredientCommand(new IngredientDto("test", "non-existent-category", null, null))))
      .isInstanceOf(UnknownCategoryException.class);
  }
}
