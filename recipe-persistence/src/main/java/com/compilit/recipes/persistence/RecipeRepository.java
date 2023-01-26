package com.compilit.recipes.persistence;

import com.compilit.recipes.entities.Category;
import com.compilit.recipes.entities.Recipe;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface RecipeRepository extends CrudRepository<Recipe, Long> {

  //Todo: fix. I can't for the life of me figure out why this doesn't work.
  @Query("""
    SELECT ingredientSpecifier.recipe
    FROM IngredientSpecifier ingredientSpecifier
    WHERE (:category IS NULL OR ingredientSpecifier.ingredient.category = :category )
    AND (:including_ingredient IS NULL OR ingredientSpecifier.ingredient.name = :including_ingredient)
    AND (:excluding_ingredient IS NULL OR ingredientSpecifier.ingredient.name != :excluding_ingredient)
    AND (:serves IS NULL OR ingredientSpecifier.recipe.serves >= :serves)
    """)
  List<Recipe> findAllByFilters(@Param("category") Category category,
                                @Param("including_ingredient") String includingIngredient,
                                @Param("excluding_ingredient") String excludingIngredient,
                                @Param("serves") Integer serves);

}
