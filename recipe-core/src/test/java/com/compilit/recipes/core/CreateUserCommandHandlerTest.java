package com.compilit.recipes.core;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.compilit.recipes.core.api.CreateUserCommand;
import com.compilit.recipes.core.api.PersistencePort;
import com.compilit.recipes.core.api.UserDto;
import com.compilit.recipes.entities.RecipeAppUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class CreateUserCommandHandlerTest {

  @Mock
  private PersistencePort<RecipeAppUser> recipeAppUserPersistencePort;
  @Mock
  private PasswordEncoder passwordEncoder;
  @InjectMocks
  private CreateUserCommandHandler createUserCommandHandler;

  @Test
  void handle_validInput_shouldCreateUser() {
    var username = "test";
    var password = "test";
    var authority = "USER";
    when(passwordEncoder.encode(password)).thenReturn(password);
    createUserCommandHandler.handle(new CreateUserCommand(new UserDto(username, password)));
    verify(recipeAppUserPersistencePort).persist(argThat(recipeAppUser ->
                                                           recipeAppUser.getUsername().equals(username)
                                                             && recipeAppUser.getPassword().equals(password)
                                                             && recipeAppUser.getAuthorities()
                                                                             .stream()
                                                                             .allMatch(grantedAuthority -> grantedAuthority.getAuthority()
                                                                                                                           .equals(
                                                                                                                             authority))
                                                             && recipeAppUser.isEnabled()
                                                             && recipeAppUser.isAccountNonExpired()
                                                             && recipeAppUser.isAccountNonLocked()
                                                             && recipeAppUser.isCredentialsNonExpired()
    ));
  }

  @Test
  void handle_invalidInput_shouldThrowException() {
  }
}