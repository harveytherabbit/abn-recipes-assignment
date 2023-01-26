package com.compilit.recipes.core.exceptions;

public class UnknownCategoryException extends RuntimeException {
  public UnknownCategoryException(String category) {
    super("Unknown category: " + category);
  }
}
