package com.compilit.recipes.core.api;

import com.compilit.mediator.api.Command;

public record CreateUserCommand(UserDto userDto) implements Command<Void> {
}
