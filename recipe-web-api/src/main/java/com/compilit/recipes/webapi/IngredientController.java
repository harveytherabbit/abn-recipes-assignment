package com.compilit.recipes.webapi;

import com.compilit.mediator.api.CommandDispatcher;
import com.compilit.recipes.core.api.CreateIngredientCommand;
import com.compilit.recipes.core.api.IngredientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
class IngredientController {

  private final CommandDispatcher commandDispatcher;

  @PostMapping
  public void createIngredient(@RequestBody IngredientDto ingredientDto) {
    commandDispatcher.dispatch(new CreateIngredientCommand(ingredientDto));
  }
}
