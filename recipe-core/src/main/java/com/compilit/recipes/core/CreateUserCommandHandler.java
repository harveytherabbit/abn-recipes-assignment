package com.compilit.recipes.core;

import com.compilit.mediator.api.CommandHandler;
import com.compilit.recipes.core.api.CreateUserCommand;
import com.compilit.recipes.core.api.PersistencePort;
import com.compilit.recipes.entities.Authority;
import com.compilit.recipes.entities.RecipeAppUser;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class CreateUserCommandHandler implements CommandHandler<CreateUserCommand, Void> {

  private final PersistencePort<RecipeAppUser> recipeAppUserPersistencePort;
  private final PasswordEncoder passwordEncoder;
  @Override
  public Void handle(CreateUserCommand command) {
    log.info("Creating new user {}", command.userDto());
    var userDto = command.userDto();
    var recipeAppUser = new RecipeAppUser(
      userDto.username(),
      passwordEncoder.encode(userDto.password()),
      List.of(new Authority("USER")),
      true,
      true,
      true,
      true,
      new ArrayList<>()
    );
    recipeAppUserPersistencePort.persist(recipeAppUser);
    return null;
  }
}
