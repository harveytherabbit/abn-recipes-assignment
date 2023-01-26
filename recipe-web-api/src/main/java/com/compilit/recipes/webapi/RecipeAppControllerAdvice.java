package com.compilit.recipes.webapi;

import com.compilit.recipes.core.exceptions.UnknownCategoryException;
import com.compilit.recipes.core.exceptions.UnknownIngredientException;
import com.compilit.recipes.core.exceptions.UnknownUserException;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RecipeAppControllerAdvice {

  @ExceptionHandler(UnknownIngredientException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public @ResponseBody ErrorResponseDto handleUnknownIngredient() {
    var message = "Provided ingredient was not registered";
    log.error(message);
    return ErrorResponseDto.builder()
                           .error("unknown ingredient")
                           .message(message)
                           .build();
  }
  @ExceptionHandler(UnknownCategoryException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public @ResponseBody ErrorResponseDto handleUnknownCategory() {
    var message = "Provided category does not exist";
    log.error(message);
    return ErrorResponseDto.builder()
                           .error("unknown category")
                           .message(message)
                           .build();
  }
  @ExceptionHandler(UnknownUserException.class)
  @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
  public @ResponseBody ErrorResponseDto handleUnknownUser() {
    var message = "Provided credentials are unknown";
    log.error(message);
    return ErrorResponseDto.builder()
                           .error("unknown user")
                           .message(message)
                           .build();
  }

  @Builder
  record ErrorResponseDto(String message, String error) {}
}
