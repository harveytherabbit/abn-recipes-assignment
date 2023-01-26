package com.compilit.recipes.core;

import com.compilit.mediator.api.CommandHandler;
import com.compilit.recipes.core.api.CreateIngredientCommand;
import com.compilit.recipes.core.api.PersistencePort;
import com.compilit.recipes.core.exceptions.UnknownCategoryException;
import com.compilit.recipes.entities.Category;
import com.compilit.recipes.entities.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CreateIngredientCommandHandler implements CommandHandler<CreateIngredientCommand, Void> {

  private final PersistencePort<Ingredient> ingredientPersistencePort;
  @Override
  public Void handle(CreateIngredientCommand command) {
    var ingredientDto = command.ingredientDto();
    var ingredientName = ingredientDto.name();
    var categoryName = ingredientDto.category();
    var category = Category.get(categoryName).orElseThrow(() -> new UnknownCategoryException(categoryName));
    var ingredient = new Ingredient(ingredientName, category);
    ingredientPersistencePort.persist(ingredient);
    return null;
  }
}
