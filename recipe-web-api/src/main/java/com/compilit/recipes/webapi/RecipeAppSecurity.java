package com.compilit.recipes.webapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class RecipeAppSecurity {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http)
        throws Exception {
        http
            .authorizeHttpRequests((authorizeRequests) ->
                                   authorizeRequests
                                       .requestMatchers("/account", "/ingredients").permitAll()
                                       .requestMatchers("/recipes").hasAuthority("USER")
            )
            .httpBasic()
            .and()
            .csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}