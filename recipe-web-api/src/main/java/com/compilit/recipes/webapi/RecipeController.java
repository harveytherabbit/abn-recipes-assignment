package com.compilit.recipes.webapi;

import com.compilit.mediator.api.CommandDispatcher;
import com.compilit.mediator.api.QueryDispatcher;
import com.compilit.recipes.core.api.CreateRecipeCommand;
import com.compilit.recipes.core.api.FindRecipesByFiltersQuery;
import com.compilit.recipes.core.api.GetCurrentUserQuery;
import com.compilit.recipes.core.api.RecipeDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes")
class RecipeController {

  private final QueryDispatcher queryDispatcher;
  private final CommandDispatcher commandDispatcher;

  @PostMapping
  public void createRecipe(Authentication authentication, @RequestBody RecipeDto recipeDto) {
    var currentUser = queryDispatcher.dispatch(new GetCurrentUserQuery(authentication));
    commandDispatcher.dispatch(new CreateRecipeCommand(recipeDto, currentUser));
  }

  @GetMapping
  public List<RecipeDto> getRecipesBy(
    Authentication authentication,
    @RequestParam(value = "category", required = false) String category,
    @RequestParam(value = "included_ingredient", required = false) String includedIngredient,
    @RequestParam(value = "excluded_ingredient", required = false) String excludedIngredient,
    @RequestParam(value = "serves", required = false) Integer serves) {
    var currentUser = queryDispatcher.dispatch(new GetCurrentUserQuery(authentication));
    return queryDispatcher.dispatch(new FindRecipesByFiltersQuery(
      category,
      includedIngredient,
      excludedIngredient,
      serves,
      currentUser
    ));
  }
}
