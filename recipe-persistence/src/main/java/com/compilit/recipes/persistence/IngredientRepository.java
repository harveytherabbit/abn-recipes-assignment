package com.compilit.recipes.persistence;

import com.compilit.recipes.entities.Ingredient;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface IngredientRepository extends CrudRepository<Ingredient, Long> {

  Optional<Ingredient> findIngredientByName(String name);

}
