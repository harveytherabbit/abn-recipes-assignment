package com.compilit.recipes.core.api;

import com.compilit.mediator.api.Query;
import org.springframework.security.core.Authentication;

public record GetCurrentUserQuery(Authentication authentication) implements Query<LoggedInUserDto> {
}
