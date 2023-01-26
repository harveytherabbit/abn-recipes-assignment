package com.compilit.recipes.webapi;

import com.compilit.mediator.api.CommandDispatcher;
import com.compilit.recipes.core.api.CreateUserCommand;
import com.compilit.recipes.core.api.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
class UserController {

  private final CommandDispatcher commandDispatcher;

  @PostMapping
  public void createUser(@RequestBody UserDto userDto) {
    commandDispatcher.dispatch(new CreateUserCommand(userDto));
  }
}
