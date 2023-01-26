package com.compilit.recipes.application;

import com.compilit.mediator.MediatorConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "com.compilit.recipes")
@Import(MediatorConfiguration.class)
public class RecipeApplication {

  public static void main(String[] args) {
    SpringApplication.run(RecipeApplication.class);
  }
}
