package com.compilit.recipes.persistence;

import com.compilit.recipes.entities.RecipeAppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface RecipeAppUserRepository extends CrudRepository<RecipeAppUser, String> {
}
