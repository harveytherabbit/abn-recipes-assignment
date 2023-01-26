package com.compilit.recipes.core;

import com.compilit.mediator.api.QueryHandler;
import com.compilit.recipes.core.api.GetCurrentUserQuery;
import com.compilit.recipes.core.api.LoggedInUserDto;
import com.compilit.recipes.core.api.RetrievalPort;
import com.compilit.recipes.entities.RecipeAppUser;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
class RecipeAppUserDetailsService implements UserDetailsService, QueryHandler<GetCurrentUserQuery, LoggedInUserDto> {

    private final RetrievalPort<RecipeAppUser, String> userRetrievalPort;

    @Override
    public UserDetails loadUserByUsername(final String username)
        throws UsernameNotFoundException {
        Objects.requireNonNull(username, "No username was provided");
        return userRetrievalPort.findBy(username).orElseThrow();
    }

    @Override
    public LoggedInUserDto handle(GetCurrentUserQuery query) {
        var authentication = query.authentication();
        var user = (RecipeAppUser)authentication.getPrincipal();
        return new LoggedInUserDto(user.getUsername());
    }
}