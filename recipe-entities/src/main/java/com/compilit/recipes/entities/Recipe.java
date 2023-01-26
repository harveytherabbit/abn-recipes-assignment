package com.compilit.recipes.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter(AccessLevel.PACKAGE)
@Getter
@Table(name = "recipes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipe extends Model {

  @ManyToOne(targetEntity = RecipeAppUser.class)
  private RecipeAppUser owner;
  @Column(name = "name")
  private String name;
  @Column(name = "instructions")
  private String instructions;
  @OneToMany(targetEntity = IngredientSpecifier.class, cascade = CascadeType.PERSIST)
  private final List<IngredientSpecifier> ingredientSpecifiers = new ArrayList<>();
  @Column(name = "serves")
  private int serves;

  public void addIngredients(List<IngredientSpecifier> ingredients) {
    this.ingredientSpecifiers.addAll(ingredients);
  }
}
