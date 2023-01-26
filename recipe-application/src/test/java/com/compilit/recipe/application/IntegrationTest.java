package com.compilit.recipe.application;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static ru.yandex.qatools.embed.postgresql.distribution.Version.Main.V9_6;

import com.compilit.recipes.application.RecipeApplication;
import com.compilit.recipes.core.api.IngredientDto;
import com.compilit.recipes.core.api.RecipeDto;
import com.compilit.recipes.core.api.UserDto;
import com.compilit.recipes.entities.Category;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;
import ru.yandex.qatools.embed.postgresql.EmbeddedPostgres;

@ActiveProfiles("test")
@SpringBootTest(classes = {RecipeApplication.class}, webEnvironment = WebEnvironment.DEFINED_PORT)
public class IntegrationTest {

  private static final EmbeddedPostgres postgres = new EmbeddedPostgres(V9_6);
  private static final String username = "user";
  private static final String password = "password";
  private static final String baseUri = "http://localhost:8081";
  private static final String accountResource = "/account";
  private static final String recipesResource = "/recipes";
  private static final String ingredientsResource = "/ingredients";
  private static final String accountUri = baseUri + accountResource;
  private static final String recipesUri = baseUri + recipesResource;
  private static final String ingredientsUri = baseUri + ingredientsResource;
  private final String auth = username + ":" + password;
  private final byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII), false);
  private final String authHeader = "Basic " + new String(encodedAuth);
  @Autowired
  PasswordEncoder passwordEncoder;
  private final WebClient client = WebClient.create();

  @BeforeAll
  static void setup() throws IOException {
    postgres.start("localhost", 5588, "recipes", username, password);
  }

  @AfterAll
  static void destroy() {
    postgres.stop();
  }

  @Test
  void createUser_shouldReturnOK() {
    var response = client.post()
                         .uri(accountUri)
                         .bodyValue(new UserDto(
                           username,
                           password
                         ))
                         .exchange().block();
    assertThat(response.statusCode().is2xxSuccessful()).isTrue();
  }

  @Test
  void createIngredients_shouldReturnOK() {

    var response = client.post()
                         .uri(ingredientsUri)
                         .bodyValue(new IngredientDto(
                           "bread",
                           Category.VEGETARIAN.name(),
                           null, null
                         ))
                         .header("Authorization", authHeader)
                         .exchange().block();

    assertThat(response.statusCode().is2xxSuccessful()).isTrue();
  }

  @Test
  void createRecipe_shouldReturnOK() {

    var response = client.post()
                         .uri(recipesUri)
                         .bodyValue(new RecipeDto(
                           "french toast",
                           "stick 'em in the toaster",
                           List.of(new IngredientDto(
                             "bread",
                             "VEGETARIAN",
                             4d,
                             "PIECES"
                           )),
                           4
                         ))
                         .header("Authorization", authHeader)
                         .exchange().block();

    assertThat(response.statusCode().is2xxSuccessful()).isTrue();
  }

  @Test
  void getRecipe_oneParam_shouldReturnOK() {
    var response = client.get()
                         .uri(uriBuilder -> uriBuilder.scheme("http").host("localhost").port(8081)
                                                      .path(recipesResource)
                                                      .queryParam("category", "VEGETARIAN")
                                                      .build())
                         .header("Authorization", authHeader)
                         .exchange().block();

    assertThat(response.statusCode().is2xxSuccessful()).isTrue();
  }

  @Test
  void getRecipe_twoParams_shouldReturnOK() {
    var response = client.get()
                         .uri(uriBuilder -> uriBuilder.scheme("http")
                                                      .host("localhost")
                                                      .port(8081)
                                                      .path(recipesResource)
                                                      .queryParam("category", "VEGETARIAN")
                                                      .queryParam("inclusive_ingredient",List.of("bread"))
                                                      .build())
                         .header("Authorization", authHeader)
                         .exchange().block();

    assertThat(response.statusCode().is2xxSuccessful()).isTrue();
  }

  @Test
  void getRecipe_threeParams_shouldReturnOK() {
    var response =
      client.get()
            .uri(uriBuilder -> uriBuilder.scheme("http").host("localhost").port(8081)
                                         .path(recipesResource)
                                         .queryParam("category","VEGETARIAN")
                                         .queryParam("included_ingredient", List.of("bread"))
                                         .queryParam("excluded_ingredient", List.of("blurg"))
                                         .build())
            .header("Authorization", authHeader)
            .exchange().block();

    assertThat(response.statusCode()
                       .is2xxSuccessful()).isTrue();

  }

  @Test
  void getRecipe_fourParams_shouldReturnOK() {
    var response = client.get()
                         .uri(uriBuilder -> uriBuilder.scheme("http")
                                                      .host("localhost")
                                                      .port(8081)
                                                      .path(recipesResource)
                                                      .queryParam("category","VEGETARIAN")
                                                      .queryParam("included_ingredient", List.of("bread"))
                                                      .queryParam("excluded_ingredient", List.of("blurg"))
                                                      .queryParam("serves",4)
                                                      .build())
                         .header("Authorization", authHeader)
                         .exchange().block();
    assertThat(response.statusCode()
                       .is2xxSuccessful()).isTrue();
  }


}
