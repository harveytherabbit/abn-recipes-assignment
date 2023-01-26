package com.compilit.recipes.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "ingredient_specifiers")
@NoArgsConstructor
@AllArgsConstructor
public class IngredientSpecifier extends Model {

  @OneToOne(targetEntity = Ingredient.class)
  private Ingredient ingredient;
  @Column(name = "amount")
  private double amount;
  @Column(name = "unit")
  @Enumerated(EnumType.STRING)
  private Unit unit;
  @OneToOne(targetEntity = Recipe.class)
  private Recipe recipe;

  public boolean is(Category category) {
    return this.getIngredient().getCategory().equals(category);
  }
}
