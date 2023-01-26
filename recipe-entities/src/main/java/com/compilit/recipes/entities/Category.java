package com.compilit.recipes.entities;

import java.util.Arrays;
import java.util.Optional;

public enum Category {
  ALL,
  VEGAN,
  VEGETARIAN,
  NON_VEG;

  public static Optional<Category> get(String name) {
    return Arrays.stream(values()).filter(x -> x.name().equalsIgnoreCase(name)).findFirst();
  }
}
