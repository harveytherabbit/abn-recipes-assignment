package com.compilit.recipes.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FindRecipesByFiltersQueryHandlerTest {

  @InjectMocks
  private FindRecipesByFiltersQueryHandler findRecipesByFiltersQueryHandler;
  @Test
  void handle_validInput_shouldReturnRecipes() {
  }

  @Test
  void handle_invalidInput_shouldThrowException() {
  }
}