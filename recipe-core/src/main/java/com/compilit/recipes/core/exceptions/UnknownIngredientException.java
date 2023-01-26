package com.compilit.recipes.core.exceptions;

public class UnknownIngredientException extends RuntimeException {

  public UnknownIngredientException(String name) {
    super("Unknown ingredient: " + name);
  }
}
